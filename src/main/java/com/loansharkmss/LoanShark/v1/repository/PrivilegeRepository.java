package com.loansharkmss.LoanShark.v1.repository;

import com.loansharkmss.LoanShark.v1.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

    public Privilege findPrivilegeByName(String name);

    public Privilege save(Privilege privilege);

}
