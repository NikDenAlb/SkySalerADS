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
 * Model for user login.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "login")
public class Login {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    /**
     * Username.
     */
    private String username;

    /**
     * User password.
     */
    private String password;
}