package selfConstructed.SkySalerADS.service;

import selfConstructed.SkySalerADS.dto.RegisterDTO;
import selfConstructed.SkySalerADS.model.Role;

public interface AuthService {
    boolean login(String login, String password);

    boolean register(RegisterDTO register, Role role);
}
