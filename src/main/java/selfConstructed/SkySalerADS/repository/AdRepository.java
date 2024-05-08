package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import selfConstructed.SkySalerADS.model.Ad;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Integer> {
    @Query(nativeQuery = true,value = "SELECT * FROM ad WHERE author_user_id= :userId")
    List<Ad> findByAuthorId(@Param("userid")Integer userId);
}
