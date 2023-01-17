package com.taskman.service.Impl;
import java.util.Arrays;

import com.taskman.repository.UserRepository;
import com.taskman.model.entities.UserEntity;
import com.taskman.model.entities.enums.UserRoleEnum;
import com.taskman.model.services.UserServiceModel;
import com.taskman.service.UserRoleService;
import com.taskman.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           UserRoleService userRoleService,
                           ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userServiceModel.getPassword());
        userServiceModel.setPassword(encodedPassword);
        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);
        user.setUserRoles(Arrays.asList(userRoleService.findRole(UserRoleEnum.ROLE_CLIENT)));

        userRepository.save(user);
    }

    @Override
    public UserServiceModel findByEmailAndPassword(String email, String password) {
        UserServiceModel foundUser = userRepository
                .findByEmail(email)
                .map(user -> {
                    if (passwordEncoder.matches(password, user.getPassword())) {
                        return modelMapper.map(user, UserServiceModel.class);
                    }

                    return null;
                })
                .orElse(null);

        return foundUser;
    }
}
