package com.loansharkmss.LoanShark.v1.mappers.implementations;

import com.loansharkmss.LoanShark.v1.dtos.UserCreateDTO;
import com.loansharkmss.LoanShark.v1.mappers.interfaces.UserMapper;
import com.loansharkmss.LoanShark.v1.model.User;
import com.loansharkmss.LoanShark.v1.service.interfaces.RoleService;
import org.springframework.stereotype.Component;

@Component
public class LoanSharkUserMapper implements UserMapper {

    private RoleService RoleService;

    public LoanSharkUserMapper (RoleService roleService) {
        this.RoleService = roleService;
    }

    public User TestModelCreateDTOToTestModel(UserCreateDTO userCreateDTO) {
        User user = new User();

        user.setEmail(userCreateDTO.getEmail());
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(userCreateDTO.getPassword());
        user.setFirstName(userCreateDTO.getFirstName());
        user.setLastName(userCreateDTO.getLastName());

        user.getRoles().add(RoleService.loadRoleByName("ROLE_CLIENT"));
        user.setAccountExpired(false);
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);
        user.setEnabled(true);

        return user;
    }

}
