package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.SkySalerADS.model.Login;

import java.util.UUID;

public interface LoginRepository extends JpaRepository<Login, UUID> {
}
