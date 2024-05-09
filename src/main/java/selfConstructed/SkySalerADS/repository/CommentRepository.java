package selfConstructed.SkySalerADS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import selfConstructed.SkySalerADS.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
