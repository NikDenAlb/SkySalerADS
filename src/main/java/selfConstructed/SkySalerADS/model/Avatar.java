package selfConstructed.SkySalerADS.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "avatars")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avatar")
    private Integer Id;

    private String name;

    private String type;

    private String path;

    @Lob
    @Column(name = "image")
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;

    @OneToOne
    private User user;
}