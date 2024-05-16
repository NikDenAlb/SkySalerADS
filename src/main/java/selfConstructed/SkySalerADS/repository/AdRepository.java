package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.SkySalerADS.model.Ad;
import selfConstructed.SkySalerADS.model.User;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Integer> {
    List<Ad> findAllByUser(User user);
}
