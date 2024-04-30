package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.SkySalerADS.model.CreateOrUpdateAd;

import java.util.UUID;

public interface CreateOrUpdateAdRepository extends JpaRepository<CreateOrUpdateAd, UUID> {
}
