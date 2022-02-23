package builder.userservice.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class CryptoService {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String getHash(String text) {
        return encoder.encode(text);
    }

    public Boolean compareHash(String rawText, String hashedText) {
        return encoder.matches(rawText, hashedText);
    }
}
