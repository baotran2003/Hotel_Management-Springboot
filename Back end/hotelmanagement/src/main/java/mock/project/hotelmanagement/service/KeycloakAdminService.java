package mock.project.hotelmanagement.service;

import mock.project.hotelmanagement.dto.UserRegisterDto;
import mock.project.hotelmanagement.dto.UserShowDto;

import java.util.List;

public interface KeycloakAdminService {

    Boolean createUser(UserRegisterDto userRegisterDto);

    List<UserShowDto> findAllUser();

    UserShowDto findUserById(String userId);

    UserShowDto findUserByUserName(String userName);

    Boolean updateUser(UserRegisterDto userRegisterDto);

    Boolean deleteUser(String userId);
}
