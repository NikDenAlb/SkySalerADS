package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import selfConstructed.SkySalerADS.dto.CreateOrUpdateCommentDTO;
import selfConstructed.SkySalerADS.model.CreateOrUpdateAd;

@Mapper(componentModel = "spring")
public interface CreateOrUpdateCommentMapper {
    CreateOrUpdateCommentDTO toDTO(CreateOrUpdateAd createOrUpdateAd);
    CreateOrUpdateAd toModel(CreateOrUpdateCommentDTO createOrUpdateCommentDTO);
}
