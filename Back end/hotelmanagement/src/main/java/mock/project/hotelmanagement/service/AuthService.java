package mock.project.hotelmanagement.service;

import mock.project.hotelmanagement.dto.UserLoginDto;
import mock.project.hotelmanagement.entity.TokenEntity;

import java.util.Optional;

public interface AuthService {

    Optional<TokenEntity> login(UserLoginDto userLoginDto);

    String getNewAccessToken(String refreshToken);

    Boolean logout(String refreshToken);
}
