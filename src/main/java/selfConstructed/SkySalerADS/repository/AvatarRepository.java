package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.SkySalerADS.model.Avatar;
import selfConstructed.SkySalerADS.model.User;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Optional<Avatar> findAvatarByUser(User user);

    void deleteAvatarByUser(User user);
}
