package selfConstructed.SkySalerADS.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель пользователя.
 */
@Data
@NoArgsConstructor
public class User {

    /**
     * Идентификатор пользователя.
     */
    private int id;

    /**
     * Электронная почта пользователя.7
     */
    private String email;

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
     * Роль пользователя (например, "USER").
     */
    private String role;

    /**
     * URL изображения пользователя.
     */
    private String image;
}
