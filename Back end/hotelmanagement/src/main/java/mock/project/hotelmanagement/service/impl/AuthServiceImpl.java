package mock.project.hotelmanagement.service.impl;

import mock.project.hotelmanagement.dto.UserLoginDto;
import mock.project.hotelmanagement.dto.UserShowDto;
import mock.project.hotelmanagement.entity.TokenEntity;
import mock.project.hotelmanagement.service.AuthService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private KeycloakAdminServiceImpl keycloakAdminServiceImpl;

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private final String myRealm = "hotel-management-realm";

    @Value("${keycloak.resource}")
    private String myClientId;

    @Value("${keycloak.credentials.secret}")
    private String myClientSecret;

    public Optional<TokenEntity> login(UserLoginDto userLoginDto) {

        //decode password

        try {
            Keycloak keycloak = Keycloak.getInstance(serverUrl, myRealm, userLoginDto.getUserName(), userLoginDto.getPassword(),
                    myClientId, myClientSecret);
            AccessTokenResponse accessTokenResponse = keycloak.tokenManager().getAccessToken();
            UserShowDto userShowDto = keycloakAdminServiceImpl.findUserByUserName(userLoginDto.getUserName());

            TokenEntity tokenEntity = new TokenEntity(accessTokenResponse.getToken(), accessTokenResponse.getRefreshToken(),
                    accessTokenResponse.getExpiresIn(), accessTokenResponse.getRefreshExpiresIn(), userShowDto);

            keycloak.close();
            return Optional.of(tokenEntity);
        } catch (Exception e) {
            System.out.println("Login fail!");
            return Optional.empty();
        }
    }

    public String getNewAccessToken(String refreshToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", myClientId);
        map.add("client_secret", myClientSecret);
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", refreshToken);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        String url = serverUrl + "/realms/" + myRealm + "/protocol/openid-connect/token";
        try {
            ResponseEntity<AccessTokenResponse> response = restTemplate.postForEntity(url, entity, AccessTokenResponse.class);
            AccessTokenResponse accessTokenResponse = response.getBody();
            return accessTokenResponse.getToken();
        } catch (Exception exception) {
            return null;
        }
    }

    public Boolean logout(String refreshToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", myClientId);
        map.add("client_secret", myClientSecret);
        map.add("refresh_token", refreshToken);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        String url = serverUrl + "/realms/" + myRealm + "/protocol/openid-connect/logout";
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
            System.out.println("Logout success!");
            return true;
        } else {
            System.out.println("Logout fail!");
            return false;
        }
    }

}
