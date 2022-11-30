package com.loansharkmss.LoanShark.service;

import com.loansharkmss.LoanShark.exceptions.InternalServerError;
import com.loansharkmss.LoanShark.exceptions.NotFoundException;
import com.loansharkmss.LoanShark.model.TestModel;
import com.loansharkmss.LoanShark.repository.TestModelRepository;

import javax.transaction.Transactional;

public class TestService {

    TestModelRepository testModelRepository;

    public TestService(TestModelRepository testModelRepository) {
        this.testModelRepository = testModelRepository;
    }

    public TestModel findTestModelById(Integer id) {
        TestModel testModel = testModelRepository.findTestModelById(id);

        if (testModel == null)
            throw  new NotFoundException("Test model with id " + id + " not found");

        return testModel;
    }

    @Transactional
    public TestModel deleteTestModelById(Integer id) {
        TestModel testModel = findTestModelById(id);
        Integer deletedCount = testModelRepository.deleteTestModelById(id);

        if (deletedCount > 0)
            return testModel;

        throw new InternalServerError("Failed to delete test model with id " + id);
    }

}
