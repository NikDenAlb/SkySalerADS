package selfConstructed.SkySalerADS.service;

import org.springframework.transaction.annotation.Transactional;
import selfConstructed.SkySalerADS.dto.RegisterDTO;
import selfConstructed.SkySalerADS.dto.UserDTO;
import selfConstructed.SkySalerADS.model.User;
import selfConstructed.SkySalerADS.repository.UserRepository;
import selfConstructed.SkySalerADS.exception.UserNotFoundException;

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


}