package com.loansharkmss.LoanShark.v1.mappers;

import com.loansharkmss.LoanShark.v1.dtos.UserCreateDTO;
import com.loansharkmss.LoanShark.v1.model.Role;
import com.loansharkmss.LoanShark.v1.model.User;
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
