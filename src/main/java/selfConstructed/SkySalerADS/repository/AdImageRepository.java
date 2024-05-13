package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import selfConstructed.SkySalerADS.model.AdImage;

@Repository
public interface AdImageRepository extends JpaRepository<AdImage,Long> {
}