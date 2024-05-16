package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import selfConstructed.SkySalerADS.model.Ad;

import java.util.List;
import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Long> {
//    @Query(nativeQuery = true, value = "SELECT * FROM ad WHERE author_user_id= :userid")
//    List<Ad> findByAuthorId(@Param("userid") Long userId);
//
//    Optional<Ad> findAdByPk (Long adsId);
}
