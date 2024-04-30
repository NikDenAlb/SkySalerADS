package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import selfConstructed.SkySalerADS.dto.UserDTO;
import selfConstructed.SkySalerADS.model.User;

@Mapper(componentModel = "spring")
public interface UserEntity {
    UserDTO toDTO(User user);

    User toModel(UserDTO userDTO);
}
