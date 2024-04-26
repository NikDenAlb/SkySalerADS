package selfConstructed.SkySalerADS.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель для изменения пароля.
 */
@Data
@NoArgsConstructor
public class NewPassword {

    /**
     * Текущий пароль пользователя.
     */
    private String currentPassword;

    /**
     * Новый пароль пользователя.
     */
    private String newPassword;
}