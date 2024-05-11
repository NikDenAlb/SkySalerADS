package selfConstructed.SkySalerADS.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "avatars")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "avatar_id")
    private Long avatarId;
    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "media_type")
    private String mediaType;

    @Lob
    @Column(name = "preview")
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Avatar that = (Avatar) o;
        return avatarId != null && Objects.equals(avatarId, that.avatarId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}