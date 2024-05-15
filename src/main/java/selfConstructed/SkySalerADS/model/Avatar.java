package selfConstructed.SkySalerADS.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
    @Column(name = "avatar_id")
    private Long avatarId;
//    @Column(name = "file_path")
//    private String filePath;

    private String name;

    private String type;

    private String path;

    @Lob
    @Column(name = "preview")
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] data;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;
}