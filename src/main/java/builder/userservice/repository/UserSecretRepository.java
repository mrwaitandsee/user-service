package builder.userservice.repository;

import builder.userservice.entity.UserSecret;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface UserSecretRepository extends JpaRepository<UserSecret, String> {
}
