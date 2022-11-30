package com.loansharkmss.LoanShark.repository;

import com.loansharkmss.LoanShark.model.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestModelRepository extends JpaRepository<TestModel, Integer> {

    TestModel findTestModelById(Integer id);

    Integer deleteTestModelById(Integer id);
}
