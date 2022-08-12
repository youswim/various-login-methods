package own.login.jwtlogin.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import own.login.jwtlogin.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
