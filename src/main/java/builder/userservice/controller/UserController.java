package builder.userservice.controller;

/**
 *
 *      curl -X POST http://localhost:8080/api/v1/user-management/registration -H 'Content-Type: application/json' -d '{ "uname":"mrkingofspades", "email":"mrkingofspades@gmail.com", "password": "pass123" }'
 *      curl -X POST http://localhost:8080/api/v1/user-management/authentication -H 'Content-Type: application/json' -d '{ "userId":"9b1bab6d-7387-4143-ae44-b52ad9ef8e37", "password":"pass123" }'
 *
 */

import builder.userservice.dto.UserRequestDto;
import builder.userservice.dto.UserResponseDto;
import builder.userservice.dto.AuthenticationRequestDto;
import builder.userservice.dto.AuthenticationResponseDto;

import builder.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user-management")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/users/{user-id}")
    private UserResponseDto getUserById(@PathVariable(name = "user-id") UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping(path = "/users/uname/{uname}")
    private UserResponseDto getUserByUname(@PathVariable(name = "uname") String uname) {
        return userService.getUserByUname(uname);
    }

    @Validated
    @GetMapping(path = "/users/email/{email}")
    private UserResponseDto getUserByEmail(@PathVariable(name = "email") @Valid @Email String email) {
        return userService.getUserByEmail(email);
    }


    @Validated
    @PostMapping(path = "/registration")
    private UserResponseDto registration(@Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.registration(userRequestDto);
    }

    @Validated
    @PostMapping(path = "/authentication")
    private AuthenticationResponseDto authentication(@Valid @RequestBody AuthenticationRequestDto authenticationRequestDto) {
        return userService.authentication(authenticationRequestDto);
    }

    // TODO change password of user
    // TODO add password to user
    // TODO remove password from user
    // TODO connect user secret
}
