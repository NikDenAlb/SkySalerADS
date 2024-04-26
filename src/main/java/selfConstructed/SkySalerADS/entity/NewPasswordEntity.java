package selfConstructed.SkySalerADS.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель для изменения пароля.
 */
@Data
@NoArgsConstructor
public class NewPasswordEntity {

    /**
     * Текущий пароль пользователя.
     */
    private String currentPassword;

    /**
     * Новый пароль пользователя.
     */
    private String newPassword;
}