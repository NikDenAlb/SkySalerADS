package selfConstructed.SkySalerADS.service;

import selfConstructed.SkySalerADS.dto.RegisterDTO;
import selfConstructed.SkySalerADS.dto.UserDTO;

public interface UserService {
    UserDTO createUser(RegisterDTO registerDTO);

    boolean checkUserForRegisterOk(String login);

    boolean checkUserExists(String login);
}