package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.SkySalerADS.model.Ad;

public interface AdRepository extends JpaRepository<Ad, Integer> {
//    @Query(nativeQuery = true, value = "SELECT * FROM ad WHERE author_user_id= :userid")
//    List<Ad> findByAuthorId(@Param("userid") Long userId);
//
//    Optional<Ad> findAdByPk (Long adsId);
}
