package selfConstructed.SkySalerADS.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Model for changing password.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "new_password")
public class NewPassword {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
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