package builder.userservice.controller;

import builder.userservice.dto.InputUserDto;
import builder.userservice.dto.OutputUserDto;
import builder.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/user-management")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/users/{user-id}")
    private OutputUserDto getUserById(@PathVariable(name = "user-id") UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping(path = "/users/uname/{uname}")
    private OutputUserDto getUserByUname(@PathVariable(name = "uname") String uname) {
        return userService.getUserByUname(uname);
    }

    @Validated
    @GetMapping(path = "/users/email/{email}")
    private OutputUserDto getUserByEmail(@PathVariable(name = "email") @Valid @Email String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping(path = "/registration")
    private OutputUserDto registration(@Valid @RequestBody InputUserDto inputUserDto) {
        return userService.registration(inputUserDto);
    }
}
