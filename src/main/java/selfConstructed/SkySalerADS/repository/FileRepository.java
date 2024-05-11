package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.file.Files;

public interface FileRepository extends JpaRepository<Files, Long> {

    Files findByName(String name);
}