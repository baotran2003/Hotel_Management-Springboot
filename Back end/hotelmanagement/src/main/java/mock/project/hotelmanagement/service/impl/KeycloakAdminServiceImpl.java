package mock.project.hotelmanagement.service.impl;

import mock.project.hotelmanagement.exception.NotFoundException;
import mock.project.hotelmanagement.dto.UserRegisterDto;
import mock.project.hotelmanagement.dto.UserShowDto;
import mock.project.hotelmanagement.service.KeycloakAdminService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.*;

@Service
public class KeycloakAdminServiceImpl implements KeycloakAdminService {

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String myRealm;

    @Value("${keycloak.resource}")
    private String myClient;

    @Value("${keycloak.credentials.username}")
    private String username;

    @Value("${keycloak.credentials.password}")
    private String password;

    public Keycloak getKeycloakAdminInstance() {
        return Keycloak.getInstance(serverUrl, "master", username, password, "admin-cli");
    }

    public Boolean createUser(UserRegisterDto userRegisterDto) {

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(userRegisterDto.getUserName());
        userRepresentation.setFirstName(userRegisterDto.getFirstName());
        userRepresentation.setLastName(userRegisterDto.getLastName());
        userRepresentation.setEmail(userRegisterDto.getEmail());
        userRepresentation.setEnabled(Boolean.TRUE);
        //set attribute phone
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("phone", Collections.singletonList(userRegisterDto.getPhoneNumber()));
        userRepresentation.setAttributes(attributes);
        //set password
        CredentialRepresentation credentials = new CredentialRepresentation();
        credentials.setType(CredentialRepresentation.PASSWORD);
        credentials.setValue(userRegisterDto.getPassword());
        credentials.setTemporary(false);
        userRepresentation.setCredentials(Collections.singletonList(credentials));

        Keycloak keycloak = getKeycloakAdminInstance();
        Response response = keycloak.realm(myRealm).users().create(userRepresentation);
        keycloak.close();
        return response.getStatus() == 201;
    }

    public List<UserShowDto> findAllUser() {
        Keycloak keycloak = getKeycloakAdminInstance();
        List<UserRepresentation> userRepresentations = keycloak.realm(myRealm).users().list();
        List<UserShowDto> userShowDtos = new ArrayList<>();
        for (UserRepresentation userRepresentation : userRepresentations) {
            UserShowDto userShowDto = new UserShowDto();
            userShowDto.setUserId(userRepresentation.getId());
            userShowDto.setUserName(userRepresentation.getUsername());
            userShowDto.setFirstName(userRepresentation.getFirstName());
            userShowDto.setLastName(userRepresentation.getLastName());
            userShowDto.setEmail(userRepresentation.getEmail());
            //get attribute phone
            userShowDto.setPhoneNumber(userRepresentation.getAttributes().get("phone").get(0));
            //get client roles
            UserResource userResource = keycloak.realm(myRealm).users().get(userRepresentation.getId());
            List<RoleRepresentation> roleRepresentations = userResource.roles().clientLevel(myClient).listAll();
            List<String> roles = new ArrayList<>();
            for (RoleRepresentation roleRepresentation : roleRepresentations) {
                roles.add(roleRepresentation.getName());
            }
            userShowDto.setRoles(roles);

            userShowDtos.add(userShowDto);
        }
        keycloak.close();
        return userShowDtos;
    }

    public UserShowDto findUserById(String userId) {
        Keycloak keycloak = getKeycloakAdminInstance();
        try {
            UserResource userResource = keycloak.realm(myRealm).users().get(userId);
            UserRepresentation userRepresentation = userResource.toRepresentation();
            if (!userRepresentation.isEnabled()) {
                throw new NotFoundException("user does not exist!");
            } else {
                UserShowDto userShowDto = new UserShowDto();
                userShowDto.setUserId(userRepresentation.getId());
                userShowDto.setUserName(userRepresentation.getUsername());
                userShowDto.setFirstName(userRepresentation.getFirstName());
                userShowDto.setLastName(userRepresentation.getLastName());
                userShowDto.setEmail(userRepresentation.getEmail());
                //get attribute phone
                userShowDto.setPhoneNumber(userRepresentation.getAttributes().get("phone").get(0));
                //get client roles
                List<RoleRepresentation> roleRepresentations = userResource.roles().clientLevel(myClient).listAll();
                List<String> roles = new ArrayList<>();
                for (RoleRepresentation roleRepresentation : roleRepresentations) {
                    roles.add(roleRepresentation.getName());
                }
                userShowDto.setRoles(roles);
                return userShowDto;
            }
        } catch (Exception exception) {
            throw new NotFoundException("User does not exist!");
        } finally {
            keycloak.close();
        }
    }

    public UserShowDto findUserByUserName(String userName) {
        Keycloak keycloak = getKeycloakAdminInstance();
        try {
            UserRepresentation userRepresentation = keycloak.realm(myRealm).users().searchByUsername(userName, true).get(0);
            if (!userRepresentation.isEnabled()) {
                throw new NotFoundException("user name does not exist!");
            } else {
                UserShowDto userShowDto = new UserShowDto();
                userShowDto.setUserId(userRepresentation.getId());
                userShowDto.setUserName(userRepresentation.getUsername());
                userShowDto.setFirstName(userRepresentation.getFirstName());
                userShowDto.setLastName(userRepresentation.getLastName());
                userShowDto.setEmail(userRepresentation.getEmail());
                //get attribute phone
                userShowDto.setPhoneNumber(userRepresentation.getAttributes().get("phone").get(0));
                //get client roles
                UserResource userResource = keycloak.realm(myRealm).users().get(userRepresentation.getId());
                List<RoleRepresentation> roleRepresentations = userResource.roles().clientLevel(myClient).listAll();
                List<String> roles = new ArrayList<>();
                for (RoleRepresentation roleRepresentation : roleRepresentations) {
                    roles.add(roleRepresentation.getName());
                }
                userShowDto.setRoles(roles);
                return userShowDto;
            }
        } catch (Exception exception) {
            throw new NotFoundException("User does not exist!");
        } finally {
            keycloak.close();
        }
    }

    public Boolean updateUser(UserRegisterDto userRegisterDto) {
        Keycloak keycloak = getKeycloakAdminInstance();
        try {
            //get userRepresentation from username
            UsersResource usersResource = keycloak.realm(myRealm).users();
            UserRepresentation userRepresentation = usersResource.searchByUsername(userRegisterDto.getUserName(), true).get(0);
            if (!userRepresentation.isEnabled()) {
                keycloak.close();
                throw new NotFoundException("User does not exist!");
            } else {
                userRepresentation.setUsername(userRegisterDto.getUserName());
                userRepresentation.setFirstName(userRegisterDto.getFirstName());
                userRepresentation.setLastName(userRegisterDto.getLastName());
                userRepresentation.setEmail(userRegisterDto.getEmail());
                //set new phone
                Map<String, List<String>> attributes = new HashMap<>();
                attributes.put("phone", Collections.singletonList(userRegisterDto.getPhoneNumber()));
                userRepresentation.setAttributes(attributes);
                //set new password
                CredentialRepresentation credentials = new CredentialRepresentation();
                credentials.setType(CredentialRepresentation.PASSWORD);
                credentials.setValue(userRegisterDto.getPassword());
                credentials.setTemporary(false);
                userRepresentation.setCredentials(Collections.singletonList(credentials));

                UserResource userResource = usersResource.get(userRepresentation.getId());
                userResource.update(userRepresentation);
                keycloak.close();
                return true;
            }
        } catch (Exception exception) {
            throw new NotFoundException("User does not exist!");
        } finally {
            keycloak.close();
        }
    }

    public Boolean deleteUser(String userId) {
        Keycloak keycloak = getKeycloakAdminInstance();
        try {
            UserResource userResource = keycloak.realm(myRealm).users().get(userId);
            UserRepresentation userRepresentation = userResource.toRepresentation();
            if (!userRepresentation.isEnabled()) {
                throw new NotFoundException("User does not exist!");
            } else {
                userRepresentation.setEnabled(Boolean.FALSE);
                userResource.update(userRepresentation);
                return true;
            }
        } catch (Exception exception) {
            throw new NotFoundException("User does not exist!");
        } finally {
            keycloak.close();
        }
    }
}
