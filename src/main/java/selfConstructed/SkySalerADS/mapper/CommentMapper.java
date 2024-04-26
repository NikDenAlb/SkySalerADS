package selfConstructed.SkySalerADS.mapper;

import org.mapstruct.Mapper;
import selfConstructed.SkySalerADS.dto.CommentDTO;
import selfConstructed.SkySalerADS.model.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDTO toDTO(Comment comment);

    Comment toModel(CommentDTO commentDTO);
}
