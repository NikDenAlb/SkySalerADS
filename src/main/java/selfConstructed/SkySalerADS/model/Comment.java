package selfConstructed.SkySalerADS.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

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