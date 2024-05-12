package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import selfConstructed.SkySalerADS.model.Avatar;
import selfConstructed.SkySalerADS.model.User;

import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar,Long> {

    Optional<Avatar> findAvatarByUserId(User user);

    Optional<Avatar> findAvatarByAvatarId(Long avatarId);
    void deleteAvatarByUserId(User user);
}
