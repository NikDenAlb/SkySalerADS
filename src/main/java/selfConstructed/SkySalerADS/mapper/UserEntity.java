package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.transaction.annotation.Transactional;
import selfConstructed.SkySalerADS.dto.RegisterDTO;
import selfConstructed.SkySalerADS.dto.UserDTO;
import selfConstructed.SkySalerADS.model.Avatar;
import selfConstructed.SkySalerADS.model.User;

@Mapper(componentModel = "spring")
public interface UserEntity {

    @Mapping(target = "image", expression = "java(getImageLink(user.getAvatar()))")
    UserDTO toDTO(User user);

    User toModel(RegisterDTO registerDTO);


    @Transactional
    default String getImageLink(Avatar avatar) {
        if (avatar == null) {
            return null;
        }
        return "/users/image/" + avatar.getAvatarId();
    }


}
