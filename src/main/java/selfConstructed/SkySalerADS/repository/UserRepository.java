package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.SkySalerADS.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
