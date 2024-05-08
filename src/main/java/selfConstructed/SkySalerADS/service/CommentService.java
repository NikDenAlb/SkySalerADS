package selfConstructed.SkySalerADS.service;

import selfConstructed.SkySalerADS.dto.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO, Integer adId, Integer userId);

    CommentDTO updateComment(CommentDTO commentDTO);

    void deleteComment(Integer commentId, Integer userId);
}
