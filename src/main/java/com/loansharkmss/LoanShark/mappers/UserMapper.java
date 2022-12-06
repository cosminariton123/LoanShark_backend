package com.loansharkmss.LoanShark.mappers;

import com.loansharkmss.LoanShark.dtos.UserCreateDTO;
import com.loansharkmss.LoanShark.model.Role;
import com.loansharkmss.LoanShark.model.User;
import com.loansharkmss.LoanShark.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User TestModelCreateDTOToTestModel(UserCreateDTO userCreateDTO) {
        User user = new User();

        user.setEmail(userCreateDTO.getEmail());
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(userCreateDTO.getPassword());
        user.setFirstName(userCreateDTO.getFirstName());
        user.setLastName(userCreateDTO.getLastName());

        user.setRole(Role.CLIENT);

        return user;
    }

}
