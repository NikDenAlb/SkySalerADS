package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.transaction.annotation.Transactional;
import selfConstructed.SkySalerADS.dto.AdDTO;
import selfConstructed.SkySalerADS.dto.CreateOrUpdateAdDTO;
import selfConstructed.SkySalerADS.model.Ad;
import selfConstructed.SkySalerADS.model.AdImage;
import selfConstructed.SkySalerADS.model.User;

@Mapper(componentModel = "spring")
public interface AdMapper {

    @Mapping(target = "image", expression = "java(getImageLink(ad.getAdImage()))")
    @Mapping(target = "author", expression = "java(ad.getUser().getId())")
    AdDTO toDTO(Ad ad);

    @Transactional
    default String getImageLink(AdImage adImage) {
        if (adImage == null) {
            return null;
        }
        return "/users/image/" + adImage.getId();
    }

    @Mapping(target = "adImage", ignore = true)
    @Mapping(target = "pk", ignore = true)
    Ad toModel(CreateOrUpdateAdDTO preAdDTO, User user);
}