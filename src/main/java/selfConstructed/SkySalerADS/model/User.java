package selfConstructed.SkySalerADS.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    /**
     * User ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * User login.
     */
    private String username;

    /**
     * Username.
     */
    private String firstName;

    /**
     * User's last name.
     */
    private String lastName;

    /**
     * User's last name.
     */
    private String password;
    /**
     * User's phone number.
     */
    private String phone;

    /**
     * User role (for example, "USER").
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToOne
    private Avatar avatar;
}
