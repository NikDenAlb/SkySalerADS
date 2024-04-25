package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import selfConstructed.SkySalerADS.dto.NewPasswordDTO;
import selfConstructed.SkySalerADS.model.NewPassword;

@Mapper(componentModel = "spring")
public interface NewPasswordMapper {
    NewPasswordDTO toDTO(NewPassword newPassword);

    NewPassword toModel(NewPasswordDTO newPasswordDTO);
}
