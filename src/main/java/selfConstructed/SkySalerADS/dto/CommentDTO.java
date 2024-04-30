package selfConstructed.SkySalerADS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private int author;
    private String authorImage;
    private String authorFirstName;
    private long createdAt;
    private int pk;
    private String text;
}