package selfConstructed.SkySalerADS.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import selfConstructed.SkySalerADS.dto.NewPasswordDTO;
import selfConstructed.SkySalerADS.dto.RegisterDTO;
import selfConstructed.SkySalerADS.dto.UserDTO;
import selfConstructed.SkySalerADS.exception.*;
import selfConstructed.SkySalerADS.mapper.ImageMapper;
import selfConstructed.SkySalerADS.mapper.UserRegisterDTOMapper;
import selfConstructed.SkySalerADS.model.Avatar;
import selfConstructed.SkySalerADS.model.User;
import selfConstructed.SkySalerADS.repository.AvatarRepository;
import selfConstructed.SkySalerADS.repository.UserRepository;
import selfConstructed.SkySalerADS.service.UserService;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRegisterDTOMapper userRegisterDTOMapper;
    private final AvatarRepository avatarRepository;
    private final ImageMapper imageMapper;

    @Override
    public UserDTO createUser(RegisterDTO registerDTO) {
        log.info("Trying to create and save new user");
        User newUser = userRepository.save(userRegisterDTOMapper.toModel(registerDTO));
        log.info("The user with id = {} was saved ", newUser.getId());
        return userRegisterDTOMapper.toDTO(newUser);
    }

    @Override
    public boolean checkUserForRegisterOk(String login) {
        if (userRepository.findUsersByUsernameIgnoreCase(login).isPresent()) {
            throw new UserAlreadyHereException("User is found");
        }
        return true;
    }

    @Override
    public boolean checkUserExists(String login) {
        log.info("Try to check whether the login is used or not");
        userRepository.findUsersByUsernameIgnoreCase(login)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return true;
    }

    @Transactional
    @Override
    public UserDTO getUserMe() {
        return userRegisterDTOMapper.toDTO(getUserFromAuthentication());
    }

    @Transactional
    @Override
    public User getUserFromAuthentication() {
        log.info("Try to get user from authentication");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return getUser(authentication.getName());
    }

    @Transactional
    @Override
    public User getUser(String login) {
        return userRepository.findUsersByUsernameIgnoreCase(login)
                .orElseThrow(() -> new UserNotFoundException("No User with login " + login + " in DB"));
    }

    @Transactional
    @Override
    public NewPasswordDTO setPassword(NewPasswordDTO newPasswordDTO) {
        log.info("start setting new password");
        if (newPasswordDTO.getCurrentPassword().equals(newPasswordDTO.getNewPassword())) {
            throw new SamePasswordException("Новый и старый пароли совпадают");
        }
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User user = getUserFromAuthentication();
        if (!encoder.matches(newPasswordDTO.getCurrentPassword(), user.getPassword())) {
            log.debug("пароли не совпадают");
            throw new WrongOldPasswordException("Некорректный текущий пароль");
        }
        String newPass = encoder.encode(newPasswordDTO.getNewPassword());
        user.setPassword(newPass);
        User out = userRepository.save(user);
        log.info("The user with login = {} was updated ", out.getUsername());
        return newPasswordDTO;
    }

    @Transactional
    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        log.info("updating user with username = {}", userDTO.getUsername());
        if (userDTO.getFirstName() == null || userDTO.getLastName() == null || userDTO.getPhone() == null) {
            throw new NullNewDataException("Все поля должны быть заполнены");
        }
        User user = getUserFromAuthentication();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhone(userDTO.getPhone());
        User out = userRepository.save(user);
        log.info("The userDTO with id = {} is updated ", out.getId());
        return userRegisterDTOMapper.toDTO(out);
    }
    @Transactional
    @Override
    public UserDTO updateUserImage(MultipartFile file) {
        log.info("Updating image");
        User user = getUserFromAuthentication();
        if (avatarRepository.findAvatarByUserId(user).isPresent()) {
            log.info("deleting old avatar");
            avatarRepository.deleteAvatarByUserId(user);
        }
        try {
            Avatar newAvatar = imageMapper.toAvatar(file);
            newAvatar.setUserId(user);
            avatarRepository.save(newAvatar);
            log.info("Avatar is updated");
        } catch (IOException e) {
            log.warn("saving image is broken");
            throw new BrokenImageUpdateException("Не удалось сохранить картинку");
        }
        return userRegisterDTOMapper.toDTO(user);
    }
}
