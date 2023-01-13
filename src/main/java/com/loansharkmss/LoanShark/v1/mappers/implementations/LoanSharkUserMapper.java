package com.loansharkmss.LoanShark.v1.mappers.implementations;

import com.loansharkmss.LoanShark.v1.dtos.UserCreate;
import com.loansharkmss.LoanShark.v1.mappers.interfaces.UserMapper;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.service.interfaces.RoleService;
import org.springframework.stereotype.Component;

@Component
public class LoanSharkUserMapper implements UserMapper {

    private final RoleService RoleService;

    public LoanSharkUserMapper (RoleService roleService) {
        this.RoleService = roleService;
    }

    public User UserCreateDTOToUser(UserCreate userCreate) {
        User user = new User();

        user.setEmail(userCreate.getEmail());
        user.setUsername(userCreate.getUsername());
        user.setPassword(userCreate.getPassword());
        user.setFirstName(userCreate.getFirstName());
        user.setLastName(userCreate.getLastName());

        user.getRoles().add(RoleService.loadRoleByName("ROLE_CLIENT"));
        user.getRoles().add(RoleService.loadRoleByName("ROLE_ADMIN"));
        user.setAccountExpired(false);
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);
        user.setEnabled(true);

        return user;
    }

}
