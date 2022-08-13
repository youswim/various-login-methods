package own.login.jwtlogin.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import own.login.jwtlogin.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
