package selfConstructed.SkySalerADS.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import selfConstructed.SkySalerADS.dto.CommentDTO;
import selfConstructed.SkySalerADS.exception.CommentNotFoundException;
import selfConstructed.SkySalerADS.exception.UnauthorizedException;
import selfConstructed.SkySalerADS.exception.UserNotFoundException;
import selfConstructed.SkySalerADS.mapper.CommentMapper;
import selfConstructed.SkySalerADS.model.Ad;
import selfConstructed.SkySalerADS.model.Comment;
import selfConstructed.SkySalerADS.model.User;
import selfConstructed.SkySalerADS.repository.AdRepository;
import selfConstructed.SkySalerADS.repository.CommentRepository;
import selfConstructed.SkySalerADS.repository.UserRepository;
import selfConstructed.SkySalerADS.service.CommentService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private AdRepository adRepository;

    private UserRepository userRepository;

    private CommentMapper commentMapper;

    /**
     * Creates a new comment for an advertisement.
     *
     * @param commentDTO the DTO object containing the information about the comment to be created
     * @param adId       the identifier of the advertisement for which the comment is created
     * @param userId     the identifier of the user creating the comment
     * @return the created comment as a DTO object
     */
    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer adId, Integer userId) {
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new IllegalArgumentException("Ad not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        Comment comment = commentMapper.toModel(commentDTO);
        comment.setText(comment.getText());
        comment.setCreatedAt(comment.getCreatedAt());
        comment.setAuthorImage(user.getImage());
        comment.setAuthorFirstName(user.getFirstName());
        comment.setAuthor(user);

        return commentMapper.toDTO(commentRepository.save(comment));
    }

    /**
     * Updates the given comment.
     *
     * @param commentDTO The comment DTO containing the updated information.
     * @return The updated comment DTO.
     */
    @Override
    public CommentDTO updateComment(CommentDTO commentDTO) {

        Optional<Comment> optionalComment = commentRepository.findById(commentDTO.getPk());

        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();

            existingComment.setText(commentDTO.getText());
            existingComment.setCreatedAt(existingComment.getCreatedAt());

            Comment updatedComment = commentRepository.save(existingComment);

            return commentMapper.toDTO(updatedComment);
        } else {
            throw new CommentNotFoundException("Comment not found with ID: " + commentDTO.getPk());
        }
    }

    /**
     * Deletes a comment.
     *
     * @param commentId The ID of the comment to delete.
     * @param userId    The ID of the user attempting to delete the comment.
     * @throws CommentNotFoundException if the comment with the given ID is not found.
     * @throws UnauthorizedException    if the user does not have permission to delete the comment.
     */
    @Override
    public void deleteComment(Integer commentId, Integer userId) throws CommentNotFoundException, UnauthorizedException {

        Optional<Comment> optionalComment = commentRepository.findById(commentId);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            if (!comment.getAuthor().getId().equals(userId)) {
                throw new UnauthorizedException("User is not authorized to delete this comment.");
            }

            commentRepository.delete(comment);
        } else {
            throw new CommentNotFoundException("Comment not found with ID: " + commentId);
        }
    }
}
