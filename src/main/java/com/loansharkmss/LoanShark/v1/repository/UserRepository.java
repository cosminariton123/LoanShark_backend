package com.loansharkmss.LoanShark.v1.repository;

import com.loansharkmss.LoanShark.v1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    Integer deleteUserById(Long id);
}
