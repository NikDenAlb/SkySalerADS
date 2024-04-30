package selfConstructed.SkySalerADS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Model for changing password.
 */
@Data
@NoArgsConstructor
@Entity
public class NewPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    /**
     * Current user password.
     */
    private String currentPassword;

    /**
     * New user password.
     */
    private String newPassword;
}