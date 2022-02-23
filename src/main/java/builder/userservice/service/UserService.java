package builder.userservice.service;

import builder.userservice.dto.UserRequestDto;
import builder.userservice.dto.UserResponseDto;
import builder.userservice.dto.ActionResponseDto;
import builder.userservice.dto.AuthenticationRequestDto;
import builder.userservice.dto.ChangePasswordRequestDto;

import builder.userservice.entity.User;
import builder.userservice.entity.UserSecret;
import builder.userservice.entity.PasswordSecret;

import builder.userservice.repository.UserRepository;
import builder.userservice.repository.PasswordSecretRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private final CryptoService cryptoService;
    private final UserRepository userRepository;
    private final PasswordSecretRepository passwordSecretRepository;

    public UserResponseDto getUserById(UUID id) {
        var user = userRepository.findByIdEquals(id.toString());
        return new UserResponseDto(UUID.fromString(user.getId()), user.getUname(), user.getEmail());
    }

    public UserResponseDto getUserByUname(String uname) {
        var user = userRepository.findByUnameEquals(uname);
        return new UserResponseDto(UUID.fromString(user.getId()), user.getUname(), user.getEmail());
    }

    public UserResponseDto getUserByEmail(String email) {
        var user = userRepository.findByEmailEquals(email);
        return new UserResponseDto(UUID.fromString(user.getId()), user.getUname(), user.getEmail());
    }

    public UserResponseDto registration(UserRequestDto userRequestDto) {
        var hash = cryptoService.getHash(userRequestDto.getPassword());
        var user = User.builder()
                .id(UUID.randomUUID().toString())
                .uname(userRequestDto.getUname())
                .email(userRequestDto.getEmail())
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

        return new UserResponseDto(UUID.fromString(user.getId()), user.getUname(), user.getEmail());
    }

    public ActionResponseDto authentication(AuthenticationRequestDto authenticationRequestDto) {
        try {
            var user = userRepository.getById(authenticationRequestDto.getUserId());
            var passwordSecrets = user.getUserSecret().getPasswordSecretList();

            for (PasswordSecret passwordSecret : passwordSecrets) {
                var hashedPassword = passwordSecret.getPassword();

                if (cryptoService.compareHash(authenticationRequestDto.getPassword(), hashedPassword))
                    return new ActionResponseDto(true, "Authentication passed successfully.");
            }

            return new ActionResponseDto(false, "Authentication failed. Incorrect password.");
        } catch (Exception e) {
            return new ActionResponseDto(false, "Authentication failed. User not found.");
        }
    }

    public ActionResponseDto changePassword(ChangePasswordRequestDto changePasswordRequestDto) {
        try {
            var user = userRepository.getById(changePasswordRequestDto.getUserId());
            var passwordSecrets = user.getUserSecret().getPasswordSecretList();

            for (PasswordSecret passwordSecret : passwordSecrets) {
                var hashedPassword = passwordSecret.getPassword();

                if (cryptoService.compareHash(changePasswordRequestDto.getOldPassword(), hashedPassword)) {
                    var newHash = cryptoService.getHash(changePasswordRequestDto.getNewPassword());

                    passwordSecretRepository.save(
                            PasswordSecret.builder()
                                    .id(passwordSecret.getId())
                                    .password(newHash)
                                    .build()
                    );

                    return new ActionResponseDto(true, "Password has been changed.");
                }
            }

            return new ActionResponseDto(false, "Can't change password. Incorrect password.");
        } catch (Exception e) {
            return new ActionResponseDto(false, "Can't change password. User not found.");
        }
    }
}
