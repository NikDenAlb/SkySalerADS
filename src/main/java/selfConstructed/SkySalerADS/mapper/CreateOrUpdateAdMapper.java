package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import selfConstructed.SkySalerADS.dto.CreateOrUpdateAdDTO;
import selfConstructed.SkySalerADS.model.CreateOrUpdateAd;

@Mapper(componentModel = "spring")
public interface CreateOrUpdateAdMapper {
    CreateOrUpdateAdDTO toDTO(CreateOrUpdateAd CreateOrUpdateAdEntity);

    CreateOrUpdateAd toModel(CreateOrUpdateAdDTO createOrUpdateAdDTO);
}
