package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.SkySalerADS.model.Files;


public interface FileRepository extends JpaRepository<Files, Long> {

    Files findByName(String name);
}