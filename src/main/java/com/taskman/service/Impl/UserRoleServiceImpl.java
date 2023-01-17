package com.taskman.service.Impl;

import com.taskman.repository.UserRoleRepository;
import com.taskman.model.entities.UserRoleEntity;
import com.taskman.model.entities.enums.UserRoleEnum;
import com.taskman.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void initUserRoles() {
        if(userRoleRepository.count() == 0) {
            UserRoleEntity admin = new UserRoleEntity(UserRoleEnum.ROLE_ADMIN);
            UserRoleEntity agent = new UserRoleEntity(UserRoleEnum.ROLE_AGENT);
            UserRoleEntity client = new UserRoleEntity(UserRoleEnum.ROLE_CLIENT);

            List<UserRoleEntity> roles = Arrays.asList(admin, agent, client);
            userRoleRepository.saveAll(roles);
        }
    }

    @Override
    public UserRoleEntity findRole(UserRoleEnum userRoleNameEnum) {
        return userRoleRepository
                .findByRole(userRoleNameEnum)
                .orElse(null);
    }
}
