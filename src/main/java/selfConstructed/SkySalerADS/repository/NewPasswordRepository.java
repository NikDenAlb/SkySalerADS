package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.SkySalerADS.model.NewPassword;

import java.util.UUID;

public interface NewPasswordRepository extends JpaRepository<NewPassword, UUID> {
}
