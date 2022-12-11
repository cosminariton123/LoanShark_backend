package com.loansharkmss.LoanShark.config;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DefaultConfigFlagRepository extends JpaRepository<DefaultConfigFlag, Integer> {

    public DefaultConfigFlag findDefaultConfigFlagById(Integer id);

}
