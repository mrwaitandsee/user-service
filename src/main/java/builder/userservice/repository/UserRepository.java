package builder.userservice.repository;

import builder.userservice.entity.User;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByIdEquals(@NonNull String id);
    User findByUnameEquals(@NonNull String uname);
    User findByEmailEquals(@NonNull String email);
}
