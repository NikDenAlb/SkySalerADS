package selfConstructed.SkySalerADS.mapper;

import selfConstructed.SkySalerADS.dto.CommentsAllDTO;
import selfConstructed.SkySalerADS.model.Comment;

public interface CommentsAllMapper {
    CommentsAllDTO toDTO(Comment comment);

    Comment toModel(CommentsAllDTO commentsAllDTO);
}
