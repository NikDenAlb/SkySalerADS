package selfConstructed.SkySalerADS.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
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