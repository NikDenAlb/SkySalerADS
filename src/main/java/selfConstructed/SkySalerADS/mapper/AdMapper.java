package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.transaction.annotation.Transactional;
import selfConstructed.SkySalerADS.dto.AdDTO;
import selfConstructed.SkySalerADS.model.Ad;
import selfConstructed.SkySalerADS.model.AdImage;

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

//    @Mapping(target = "pk", source = "pk")
//    @Mapping(target = "author.id", source = "author")
//    @Mapping(target = "image", ignore = true)
//    Ad toModel(AdDTO adDTO);


//    @Mapping(target = "price", source = "preAdDto.price")
//    @Mapping(target = "title", source = "preAdDto.title")
//    Ad toModel(PreAdDTO preAdDto, User user);
//
//    @Transactional
//    default String[] getImageLink(List<AdImage> adImages) {
//        String[] arrayLinks = new String[1];
//        arrayLinks[0] = "/ads/image/" + adImages.get(0).getId();
//        return arrayLinks;
//    }
}
