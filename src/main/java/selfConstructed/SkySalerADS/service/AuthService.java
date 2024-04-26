package selfConstructed.SkySalerADS.service;

import selfConstructed.SkySalerADS.dto.RegisterDTO;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterDTO register);
}
