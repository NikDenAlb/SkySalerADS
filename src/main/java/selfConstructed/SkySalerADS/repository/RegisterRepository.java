package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.SkySalerADS.model.Register;

import java.util.UUID;

public interface RegisterRepository extends JpaRepository<Register, UUID> {
}
