package mock.project.hotelmanagement.controller;

import mock.project.hotelmanagement.dto.DataResponse;
import mock.project.hotelmanagement.dto.UserRegisterDto;
import mock.project.hotelmanagement.service.impl.KeycloakAdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private KeycloakAdminServiceImpl keycloakAdminServiceImpl;

    @PostMapping("/create")
    public DataResponse handlePostRequest(@RequestBody UserRegisterDto userRegisterDto) {
        if (keycloakAdminServiceImpl.createUser(userRegisterDto)) {
            return new DataResponse(null, "Created success!", Boolean.TRUE, 200);
        }
        return new DataResponse("Create user fail!", "Please check user information!", Boolean.FALSE, 400);
    }

    @GetMapping("/find-all")
    public DataResponse getAllUsers() {
        return new DataResponse(null, "Get all user success!", keycloakAdminServiceImpl.findAllUser(), 200);
    }

    @GetMapping("/find-user-by-id/{userId}")
    public DataResponse findById(@PathVariable String userId) {
        return new DataResponse(null, "Find user by Id success!", keycloakAdminServiceImpl.findUserById(userId), 200);
    }

    @GetMapping("/find-user-by-username/{userName}")
    public DataResponse findByUserName(@PathVariable String userName) {
        return new DataResponse(null, "Find user by username success!", keycloakAdminServiceImpl.findUserByUserName(userName), 200);
    }

    @PutMapping("/update")
    public DataResponse updateUser(@RequestBody UserRegisterDto userRegisterDto) {
        return new DataResponse(null, "Update user success!", keycloakAdminServiceImpl.updateUser(userRegisterDto), 200);
    }

    @DeleteMapping("/delete/{userId}")
    public DataResponse deleteUser(@PathVariable String userId) {
        return new DataResponse(null, "Delete user success!", keycloakAdminServiceImpl.deleteUser(userId), 200);
    }
}
