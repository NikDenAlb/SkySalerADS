package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import selfConstructed.SkySalerADS.dto.RegisterDTO;
import selfConstructed.SkySalerADS.dto.UserDTO;
import selfConstructed.SkySalerADS.model.User;

@Mapper(componentModel = "spring")
public interface UserRegisterDTOMapper {

//    @Mapping(target = "image", expression = "java(getImageLink(user.getAvatar()))")
    UserDTO toDTO(User user);

/*+*/    User toModel(RegisterDTO registerDTO);


//    @Transactional
//    default String getImageLink(Avatar avatar) {
//        if (avatar == null) {
//            return null;
//        }
//        return "/users/image/" + avatar.getAvatarId();
//    }


}
