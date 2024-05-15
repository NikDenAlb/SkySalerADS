package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.SkySalerADS.model.Avatar;
import selfConstructed.SkySalerADS.model.User;

import java.util.Optional;

public interface AvatarFileRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findAvatarByUserId(User user);

    void deleteAvatarByUserId(User user);
}
