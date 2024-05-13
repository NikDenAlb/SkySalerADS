package selfConstructed.SkySalerADS.service;

import selfConstructed.SkySalerADS.dto.CommentDTO;

/**
 * The {@code CommentService} interface provides methods for managing comments on advertisements.
 * Implementing classes should define the behavior for creating, updating, and deleting comments.
 *
 * @author shinkevich oleg
 */
public interface CommentService {

    /**
     * Creates a new comment on an advertisement.
     *
     * @param commentDTO the DTO object containing the information about the comment to be created
     * @param adId       the identifier of the advertisement on which the comment is being made
     * @param userId     the identifier of the user creating the comment
     * @return the created comment as a DTO object
     */
    CommentDTO createComment(CommentDTO commentDTO, Long adId, Integer userId);

    /**
     * Updates a comment.
     *
     * @param commentDTO the DTO object containing the information about the comment to be updated
     * @return the updated comment as a DTO object
     */
    CommentDTO updateComment(CommentDTO commentDTO);

    /**
     * Deletes a comment by its identifier.
     *
     * @param commentId the identifier of the comment to delete
     * @param userId    the identifier of the user attempting to delete the comment
     */
    void deleteComment(Long commentId, Long userId);
}

