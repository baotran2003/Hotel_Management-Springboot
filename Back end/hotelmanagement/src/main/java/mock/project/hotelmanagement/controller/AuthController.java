package mock.project.hotelmanagement.controller;


import mock.project.hotelmanagement.dto.DataResponse;
import mock.project.hotelmanagement.dto.UserLoginDto;
import mock.project.hotelmanagement.entity.TokenEntity;
import mock.project.hotelmanagement.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthServiceImpl authServiceImpl;

    @PostMapping("/login")
    public DataResponse login(@RequestBody UserLoginDto userLoginDto) {
        Optional<TokenEntity> tokenEntity = authServiceImpl.login(userLoginDto);
        if (tokenEntity.isPresent()) {
            return new DataResponse(null, "Login success!", tokenEntity, 200);
        }
        return new DataResponse("Login fail!", "Username or Password is invalid!", null, 400);
    }

    @PostMapping("/new-access-token")
    public DataResponse getNewAccessToken(@RequestParam("refreshToken") String refreshToken) {
        String newAccessToken = authServiceImpl.getNewAccessToken(refreshToken);
        if (newAccessToken != null) {
            return new DataResponse(null, "Get new accessToken success!", newAccessToken, 200);
        }
        return new DataResponse("Can't get new accessToken!", "refreshToken is invalid!", null, 400);
    }

    @PostMapping("/logout")
    public DataResponse logout(@RequestParam("refreshToken") String refreshToken) {
        if (authServiceImpl.logout(refreshToken)) {
            return new DataResponse(null, "Logout success!", true, 200);
        } else {
            return new DataResponse(null, "Logout fail!", false, 400);
        }
    }
}
