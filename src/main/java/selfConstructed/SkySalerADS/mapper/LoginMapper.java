package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import selfConstructed.SkySalerADS.dto.LoginDTO;
import selfConstructed.SkySalerADS.model.Login;

@Mapper(componentModel = "spring")
public interface LoginMapper {
    LoginDTO toDTO(Login login);

    Login toModel(LoginDTO loginDTO);
}
