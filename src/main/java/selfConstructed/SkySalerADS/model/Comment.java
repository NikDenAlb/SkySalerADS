package selfConstructed.SkySalerADS.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {
    /**
     * Author of the comment.
     */
    @ManyToOne
    private User user;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    /**
     * Comment text.
     */
    private String text;
}