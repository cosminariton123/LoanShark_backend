package com.loansharkmss.LoanShark.v1.service.implementations;

import com.loansharkmss.LoanShark.v1.exceptions.NotFoundException;
import com.loansharkmss.LoanShark.v1.model.Privilege;
import com.loansharkmss.LoanShark.v1.repository.PrivilegeRepository;
import com.loansharkmss.LoanShark.v1.service.interfaces.PrivilegeService;
import org.springframework.stereotype.Service;

@Service
public class LoanSharkPrivilegeService implements PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    public LoanSharkPrivilegeService (PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    public Privilege loadPrivilegeByName(String name) {
        Privilege privilege = privilegeRepository.findPrivilegeByName(name);

        if (privilege == null)
            throw new NotFoundException("Privilege with name " + name + " not found");

        return privilege;
    }

}
