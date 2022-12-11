package com.loansharkmss.LoanShark.v1.service.interfaces;

import com.loansharkmss.LoanShark.v1.model.Privilege;
import org.springframework.stereotype.Service;

@Service
public interface PrivilegeService {

    Privilege loadPrivilegeByName(String name);

}
