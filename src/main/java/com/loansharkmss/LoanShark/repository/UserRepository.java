package com.loansharkmss.LoanShark.repository;

import com.loansharkmss.LoanShark.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    Integer deleteUserById(Integer id);
}
