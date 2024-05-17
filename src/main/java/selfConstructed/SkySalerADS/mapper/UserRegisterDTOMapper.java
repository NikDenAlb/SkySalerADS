package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import selfConstructed.SkySalerADS.dto.RegisterDTO;
import selfConstructed.SkySalerADS.dto.UserDTO;
import selfConstructed.SkySalerADS.model.User;

@Mapper(componentModel = "spring")
public interface UserRegisterDTOMapper {
    UserDTO toDTO(User user);

    User toModel(RegisterDTO registerDTO);
}
