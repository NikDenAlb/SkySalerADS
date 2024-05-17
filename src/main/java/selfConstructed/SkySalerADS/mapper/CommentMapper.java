package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.transaction.annotation.Transactional;
import selfConstructed.SkySalerADS.dto.CommentDTO;
import selfConstructed.SkySalerADS.model.Avatar;
import selfConstructed.SkySalerADS.model.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
//    CommentDTO toDTO(Comment comment);
//
//    Comment toModel(CommentDTO commentDTO);

    @Mapping(target = "author", source = "comment.user.id")
    @Mapping(target = "authorImage", expression = "java(getImageLink(comment.getUser().getAvatar()))")
    @Mapping(target = "authorFirstName", source = "comment.user.firstName")
    @Mapping(target = "createdAt",ignore = true)
    CommentDTO toDto(Comment comment);


    @Transactional
    default String getImageLink(Avatar avatar) {
        if (avatar == null) {
            return null;
        }
        return "/users/image/" + avatar.getId();
    }
}
