package selfConstructed.SkySalerADS.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import selfConstructed.SkySalerADS.dto.NewPasswordDTO;
import selfConstructed.SkySalerADS.dto.RegisterDTO;
import selfConstructed.SkySalerADS.dto.UpdateUserDTO;
import selfConstructed.SkySalerADS.dto.UserDTO;
import selfConstructed.SkySalerADS.model.User;
import selfConstructed.SkySalerADS.repository.UserRepository;
import selfConstructed.SkySalerADS.exception.*;

import javax.validation.constraints.Null;
import java.io.IOException;

public interface UserService {
    UserDTO createUser(RegisterDTO registerDTO);

    boolean checkUserForRegisterOk(String login);

    boolean checkUserExists(String login);

    /**
     * Convert {@link User} to {@link UserDTO}
     */
    UserDTO getUserMe();

    /**
     * Get {@link User} from Authentication
     */
    @Transactional
    User getUserFromAuthentication();

    /**
     * Get {@link User} by login (ignore case) in database via method {@link UserRepository}
     *
     * @param login of User
     * @return Founded User
     * @throws UserNotFoundException if User not found in database
     */
    User getUser(String login);

    /**
     * Change current password of current {@link User}
     *
     * @throws SamePasswordException     if current password == new password
     * @throws WrongOldPasswordException if current password  != input password
     */
    void setPassword(NewPasswordDTO newPassword);

    /**
     * Update {@link User}
     *
     * @throws NullNewDataException if one of fields is {@link Null}
     */
    UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO);

    /**
     * Update Avatar
     *
     * @return Updated UserDTO
     * @throws BrokenImageUpdateException if update didn't work
     */
    UserDTO updateUserImage(MultipartFile file);

    void updateAvatar(MultipartFile file) throws IOException;
}