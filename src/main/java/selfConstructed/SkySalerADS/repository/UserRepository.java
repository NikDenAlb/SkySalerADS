package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.SkySalerADS.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUsersByUsernameIgnoreCase(String login);
}
