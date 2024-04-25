package selfConstructed.SkySalerADS.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель для регистрации пользователя.
 */
@Data
@NoArgsConstructor
public class RegisterEntity {

    /**
     * Имя пользователя.
     */
    private String username;

    /**
     * Пароль пользователя.
     */
    private String password;

    /**
     * Имя пользователя.
     */
    private String firstName;

    /**
     * Фамилия пользователя.
     */
    private String lastName;

    /**
     * Телефон пользователя.
     */
    private String phone;

    /**
     * Роль пользователя.
     */
    private String role;
}
