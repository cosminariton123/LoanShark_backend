package com.loansharkmss.LoanShark.v1.service.interfaces;

import com.loansharkmss.LoanShark.v1.model.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    Role loadRoleByName(String name);

}
