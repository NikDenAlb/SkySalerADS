package selfConstructed.SkySalerADS.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "avatars")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "avatar_uuid")
    private UUID avatarUuid;

    private String name;

    private String type;

    private String path;

    @Lob
    private byte[] imageData;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;
}