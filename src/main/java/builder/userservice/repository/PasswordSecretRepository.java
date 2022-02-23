package builder.userservice.repository;

import builder.userservice.entity.PasswordSecret;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PasswordSecretRepository extends JpaRepository<PasswordSecret, String> {
}
