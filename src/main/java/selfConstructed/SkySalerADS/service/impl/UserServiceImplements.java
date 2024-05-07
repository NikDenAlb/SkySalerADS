package selfConstructed.SkySalerADS.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import selfConstructed.SkySalerADS.dto.RegisterDTO;
import selfConstructed.SkySalerADS.dto.UserDTO;
import selfConstructed.SkySalerADS.exception.UserNotFoundException;
import selfConstructed.SkySalerADS.mapper.UserEntity;
import selfConstructed.SkySalerADS.model.User;
import selfConstructed.SkySalerADS.repository.UserRepository;
import selfConstructed.SkySalerADS.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImplements implements UserService {
    private final UserRepository userRepository;
    private final UserEntity userMapper;

    @Override
    public UserDTO createUser(RegisterDTO registerDTO) {
        log.info("Trying to create and save new user");
        User newUser = userRepository.save(userMapper.toModel(registerDTO));
        log.info("The user with id = {} was saved ", newUser.getId());
        return userMapper.toDTO(newUser);
    }

    @Override
    public boolean checkUserForRegisterOk(String login) {
        if (userRepository.findUserByLoginIgnoreCase(login).isPresent()) {
            throw new UserNotFoundException();
        }
        return true;
    }

    @Override
    public boolean checkUserExists(String login) {
        log.info("Try to check whether the login is used or not");
        userRepository.findUserByLoginIgnoreCase(login)
                .orElseThrow(UserNotFoundException::new);
        return true;
    }
}
