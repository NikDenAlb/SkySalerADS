package selfConstructed.SkySalerADS.service;

import selfConstructed.SkySalerADS.dto.Register;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);
}
