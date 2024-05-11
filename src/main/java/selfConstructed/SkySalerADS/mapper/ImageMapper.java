package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;
import selfConstructed.SkySalerADS.model.Avatar;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    @Mapping(target = "avatarId", ignore = true)
    @Mapping(target = "fileSize", source = "file.size")
    @Mapping(target = "mediaType", source = "file.contentType")
    @Mapping(target = "data", source = "file.bytes")
    Avatar toAvatar(MultipartFile file) throws IOException;
}
