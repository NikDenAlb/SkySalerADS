package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import selfConstructed.SkySalerADS.dto.AdDTO;
import selfConstructed.SkySalerADS.model.Ad;

@Mapper(componentModel = "spring")
public interface AdMapper {
    AdDTO toDto(Ad ad);

    Ad toModel(AdDTO adDTO);
}
