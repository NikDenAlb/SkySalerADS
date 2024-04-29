package selfConstructed.SkySalerADS.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for changing password.
 */
@Data
@NoArgsConstructor
public class NewPassword {

    /**
     * Current user password.
     */
    private String currentPassword;

    /**
     * New user password.
     */
    private String newPassword;
}