package com.taskman.service;

import com.taskman.model.entities.UserRoleEntity;
import com.taskman.model.entities.enums.UserRoleEnum;

public interface UserRoleService {
    void initUserRoles();

    UserRoleEntity findRole(UserRoleEnum roleEnum);
}
