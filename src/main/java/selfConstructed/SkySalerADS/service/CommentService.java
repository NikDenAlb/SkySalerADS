package selfConstructed.SkySalerADS.service;

import selfConstructed.SkySalerADS.dto.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO, Long adId, Long userId);

    CommentDTO updateComment(CommentDTO commentDTO);

    void deleteComment(Long commentId, Long userId);
}
