package selfConstructed.SkySalerADS.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель для создания или обновления комментария.
 */
@Data
@NoArgsConstructor
public class CreateOrUpdateComment {

    /**
     * Текст комментария.
     */
    private String text;
}