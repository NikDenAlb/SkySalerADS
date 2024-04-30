package selfConstructed.SkySalerADS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    /**
     * Author of the comment.
     */
    private String author;

    /**
     * Link to image of comment author.
     */
    private String authorImage;

    /**
     * Comment author's name.
     */
    private String authorFirstName;

    /**
     * Date and time the comment was created.
     */
    private LocalDateTime createdAt;

    /**
     * Primary key of the comment.
     */
    private Long pk;

    /**
     * Comment text.
     */
    private String text;
}