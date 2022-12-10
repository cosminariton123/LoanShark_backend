package com.loansharkmss.LoanShark.v1.service.implementations;

import com.loansharkmss.LoanShark.v1.exceptions.NotFoundException;
import com.loansharkmss.LoanShark.v1.model.Role;
import com.loansharkmss.LoanShark.v1.repository.RoleRepository;
import com.loansharkmss.LoanShark.v1.service.interfaces.RoleService;
import org.springframework.stereotype.Service;

@Service
public class LoanSharkRoleService implements RoleService {

    private final RoleRepository roleRepository;

    public LoanSharkRoleService (RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role loadRoleByName(String name) {
        Role role = roleRepository.findRoleByName(name);

        if (role == null)
            throw new NotFoundException("Role with name " + name + " not found");

        return role;
    }
}
