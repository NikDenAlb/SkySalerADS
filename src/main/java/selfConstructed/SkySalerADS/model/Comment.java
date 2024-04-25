package selfConstructed.SkySalerADS.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    /**
     * Автор комментария.
     */
    private String author;

    /**
     * Ссылка на изображение автора комментария.
     */
    private String authorImage;

    /**
     * Имя автора комментария.
     */
    private String authorFirstName;

    /**
     * Дата и время создания комментария.
     */
    private LocalDateTime createdAt;

    /**
     * Первичный ключ комментария.
     */
    private Long pk;

    /**
     * Текст комментария.
     */
    private String text;
}