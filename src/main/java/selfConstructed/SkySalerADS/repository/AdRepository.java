package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.SkySalerADS.model.Ad;

public interface AdRepository extends JpaRepository<Ad, Long> {
}
