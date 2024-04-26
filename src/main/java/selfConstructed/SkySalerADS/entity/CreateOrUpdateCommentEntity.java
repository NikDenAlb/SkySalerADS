package selfConstructed.SkySalerADS.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель для создания или обновления комментария.
 */
@Data
@NoArgsConstructor
public class CreateOrUpdateCommentEntity {

    /**
     * Текст комментария.
     */
    private String text;
}