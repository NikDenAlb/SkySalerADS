package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import selfConstructed.SkySalerADS.dto.AdDTO;
import selfConstructed.SkySalerADS.model.Ad;

@Mapper(componentModel = "spring")
public interface AdMapper {
    @Mapping(target = "pk", source = "pk")
    @Mapping(target = "author", source = "author.id")
    AdDTO toDto(Ad ad);

    @Mapping(target = "pk", source = "pk")
    @Mapping(target = "author.id", source = "author")
    Ad toModel(AdDTO adDTO);
}
