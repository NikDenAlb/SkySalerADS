package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;
import selfConstructed.SkySalerADS.model.AdImage;
import selfConstructed.SkySalerADS.model.Avatar;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(target = "type", expression = "java(file.getContentType())")
    @Mapping(target = "image", expression = "java(file.getBytes())")
    Avatar toAvatar(MultipartFile file) throws IOException;

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "image", expression = "java(file.getBytes())")
    AdImage toAdImage(MultipartFile file) throws IOException;
}