package builder.userservice.service;

import builder.userservice.dto.InputUserDto;

import builder.userservice.dto.OutputUserDto;
import builder.userservice.entity.PasswordSecret;
import builder.userservice.entity.User;
import builder.userservice.entity.UserSecret;

import builder.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final CryptoService cryptoService;
    private final UserRepository userRepository;

    public OutputUserDto getUserById(UUID id) {
        var user = userRepository.findByIdEquals(id.toString());
        return new OutputUserDto(UUID.fromString(user.getId()), user.getUname(), user.getEmail());
    }

    public OutputUserDto getUserByUname(String uname) {
        var user = userRepository.findByUnameEquals(uname);
        return new OutputUserDto(UUID.fromString(user.getId()), user.getUname(), user.getEmail());
    }

    public OutputUserDto getUserByEmail(String email) {
        var user = userRepository.findByEmailEquals(email);
        return new OutputUserDto(UUID.fromString(user.getId()), user.getUname(), user.getEmail());
    }

    public OutputUserDto registration(InputUserDto inputUserDto) {
        var hash = cryptoService.getHash(inputUserDto.getPassword());
        var user = User.builder()
                .id(UUID.randomUUID().toString())
                .uname(inputUserDto.getUname())
                .email(inputUserDto.getEmail())
                .userSecret(
                        UserSecret.builder()
                                .id(UUID.randomUUID().toString())
                                .build()
                ).build();
        user.getUserSecret()
                .getPasswordSecretList()
                .add(
                        PasswordSecret.builder()
                                .id(UUID.randomUUID().toString())
                                .password(hash)
                                .build()
                );

        userRepository.saveAndFlush(user);

        return new OutputUserDto(UUID.fromString(user.getId()), user.getUname(), user.getEmail());
    }
}
