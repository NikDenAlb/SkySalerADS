//package selfConstructed.SkySalerADS;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import selfConstructed.SkySalerADS.dto.CommentDTO;
//import selfConstructed.SkySalerADS.exception.CommentNotFoundException;
//import selfConstructed.SkySalerADS.exception.UnauthorizedException;
//import selfConstructed.SkySalerADS.mapper.CommentMapper;
//import selfConstructed.SkySalerADS.model.Ad;
//import selfConstructed.SkySalerADS.model.Comment;
//import selfConstructed.SkySalerADS.model.Role;
//import selfConstructed.SkySalerADS.model.User;
//import selfConstructed.SkySalerADS.repository.AdRepository;
//import selfConstructed.SkySalerADS.repository.CommentRepository;
//import selfConstructed.SkySalerADS.repository.UserRepository;
//import selfConstructed.SkySalerADS.service.CommentService;
//import selfConstructed.SkySalerADS.service.impl.CommentServiceImpl;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
///**
// * The {@code CommentServiceImplTest} class contains test cases for the {@link CommentServiceImpl} class,
// * which implements the {@link CommentService} interface.
// * It tests the functionality of creating, updating, and deleting comments.
// *
// * @author shinkevich oleg
// */
//class CommentServiceImplTest {
//
//    @Mock
//    private CommentRepository commentRepository;
//
//    @Mock
//    private AdRepository adRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private CommentMapper commentMapper;
//
//    @InjectMocks
//    private CommentServiceImpl commentService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    /**
//     * Tests the {@link CommentServiceImpl#createComment} method with valid input,
//     * ensuring that it returns the created comment DTO.
//     */
//    @Test
//    void createComment_ValidInput_ReturnsCreatedCommentDTO() {
//
//        CommentDTO commentDTO = new CommentDTO();
//        commentDTO.setText("Test comment");
//        Long adId = 1L;
//        Long userId = 1L;
//        Ad ad = new Ad();
//        User user = new User();
//        Comment comment = new Comment();
//        comment.setText(commentDTO.getText());
//        comment.setCreatedAt(LocalDateTime.now());
//        comment.setAuthor(user);
//
//        when(adRepository.findById(adId)).thenReturn(Optional.of(ad));
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(commentMapper.toModel(commentDTO)).thenReturn(comment);
//        when(commentRepository.save(comment)).thenReturn(comment);
//        when(commentMapper.toDTO(comment)).thenReturn(commentDTO);
//
//        CommentDTO createdCommentDTO = commentService.createComment(commentDTO, adId, userId);
//
//        assertNotNull(createdCommentDTO);
//        assertEquals(commentDTO.getText(), createdCommentDTO.getText());
//    }
//
//    /**
//     * Tests the {@link CommentServiceImpl#updateComment} method with an existing comment,
//     * ensuring that it returns the updated comment DTO.
//     */
//    @Test
//    void updateComment_ExistingComment_ReturnsUpdatedCommentDTO() {
//
//        Long commentId = 1L;
//        CommentDTO commentDTO = new CommentDTO();
//        commentDTO.setPk(commentId);
//        commentDTO.setText("Updated test comment");
//        Comment existingComment = new Comment();
//        existingComment.setPk(commentId);
//        existingComment.setText("Test comment");
//
//        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));
//        when(commentRepository.save(existingComment)).thenReturn(existingComment);
//        when(commentMapper.toDTO(existingComment)).thenReturn(commentDTO);
//
//        CommentDTO updatedCommentDTO = commentService.updateComment(commentDTO);
//
//        assertNotNull(updatedCommentDTO);
//        assertEquals(commentDTO.getText(), updatedCommentDTO.getText());
//    }
//
//    /**
//     * Tests the {@link CommentServiceImpl#updateComment} method with a non-existing comment,
//     * ensuring that it throws a {@link CommentNotFoundException}.
//     */
//    @Test
//    void updateComment_NonExistingComment_ThrowsCommentNotFoundException() {
//
//        Long nonExistingCommentId = 1L;
//        CommentDTO commentDTO = new CommentDTO();
//        commentDTO.setPk(nonExistingCommentId);
//
//        when(commentRepository.findById(nonExistingCommentId)).thenReturn(Optional.empty());
//
//        assertThrows(CommentNotFoundException.class, () -> commentService.updateComment(commentDTO));
//    }
//
//    /**
//     * Tests the {@link CommentServiceImpl#deleteComment} method with valid comment ID and user ID,
//     * ensuring that it deletes the comment.
//     */
//    @Test
//    void deleteComment_ValidCommentIdAndUserId_DeletesComment() {
//        User user = new User();
//        user.setId(1L);
//        user.setImage("test.jpg");
//        user.setPhone("+123");
//        user.setLogin("TestLogin");
//        user.setPassword("TestPassword");
//        user.setRole(Role.USER);
//        user.setFirstName("TestName");
//
//        Long commentId = 1L;
//        Long userId = 1L;
//        Comment comment = new Comment();
//        comment.setPk(commentId);
//        comment.setAuthor(user);
//
//        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
//
//        assertDoesNotThrow(() -> commentService.deleteComment(commentId, userId));
//
//        verify(commentRepository, times(1)).delete(comment);
//    }
//
//    /**
//     * Tests the {@link CommentServiceImpl#deleteComment} method with a non-existing comment ID,
//     * ensuring that it throws a {@link CommentNotFoundException}.
//     */
//    @Test
//    void deleteComment_NonExistingComment_ThrowsCommentNotFoundException() {
//        Long nonExistingCommentId = 1L;
//        Long userId = 1L;
//
//        when(commentRepository.findById(nonExistingCommentId)).thenReturn(Optional.empty());
//
//        assertThrows(CommentNotFoundException.class, () -> commentService.deleteComment(nonExistingCommentId, userId));
//    }
//
//    /**
//     * Tests the {@link CommentServiceImpl#deleteComment} method with an unauthorized user,
//     * ensuring that it throws an {@link UnauthorizedException}.
//     */
//    @Test
//    void deleteComment_UnauthorizedUser_ThrowsUnauthorizedException() {
//        User authorizedUser = new User();
//        authorizedUser.setId(7L);
//        authorizedUser.setImage("test.jpg");
//        authorizedUser.setPhone("+123");
//        authorizedUser.setLogin("TestLogin");
//        authorizedUser.setPassword("TestPassword");
//        authorizedUser.setRole(Role.USER);
//        authorizedUser.setFirstName("TestName");
//
//        User unAuthorizedUser = new User();
//        unAuthorizedUser.setId(null);
//        unAuthorizedUser.setImage(null);
//        unAuthorizedUser.setPhone(null);
//        unAuthorizedUser.setLogin(null);
//        unAuthorizedUser.setPassword(null);
//        unAuthorizedUser.setRole(null);
//        unAuthorizedUser.setFirstName(null);
//
//        Long commentId = 1L;
//        Comment comment = new Comment();
//        comment.setPk(commentId);
//        comment.setAuthor(authorizedUser);
//
//        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
//
//        assertThrows(UnauthorizedException.class, () -> commentService.deleteComment(commentId, unAuthorizedUser.getId()));
//    }
//}
