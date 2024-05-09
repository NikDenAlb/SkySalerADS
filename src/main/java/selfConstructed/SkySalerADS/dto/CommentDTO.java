package selfConstructed.SkySalerADS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private UserDTO author;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private Long pk;
    private String text;
}