package selfConstructed.SkySalerADS.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import selfConstructed.SkySalerADS.model.Avatar;
import selfConstructed.SkySalerADS.repository.AvatarFileRepository;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AvatarFileRepositoryService {

    private final AvatarFileRepository avatarFileRepository;

    public final String FILE_PATH = "C:\\storage\\";


    public void store(MultipartFile avatar) throws IOException {
        String filePath = FILE_PATH;
        Avatar inAvatar = Avatar
                .builder()
                .name(avatar.getOriginalFilename())
                .path(FILE_PATH)
                .type(avatar.getContentType())
                .data(avatar.getBytes())
                .build();

        inAvatar = avatarFileRepository.save(inAvatar);

        avatar.transferTo(new File(filePath));

        if (inAvatar.getAvatarId() != null) {
            log.info("Avatar uploaded successfully into database");
        }
    }
}
