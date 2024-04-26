package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import selfConstructed.SkySalerADS.dto.RegisterDTO;
import selfConstructed.SkySalerADS.model.Register;

@Mapper(componentModel = "spring")
public interface RegisterMapper {
    RegisterDTO toDTO(Register register);

    Register toModel(RegisterDTO registerDTO);
}
