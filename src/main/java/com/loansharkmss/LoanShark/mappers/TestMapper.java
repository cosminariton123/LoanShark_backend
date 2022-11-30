package com.loansharkmss.LoanShark.mappers;

import com.loansharkmss.LoanShark.dtos.TestModelCreateDTO;
import com.loansharkmss.LoanShark.model.TestModel;
import com.loansharkmss.LoanShark.service.TestService;
import org.springframework.stereotype.Component;

@Component
public class TestMapper {

    TestService testService;

    public TestModel TestModelCreateDTOToTestModel(TestModelCreateDTO testModelCreateDTO) {
        TestModel testModel = new TestModel();

        testModel.setNumericalValue(testModelCreateDTO.getNumericalValue());
        testModel.setStringValue(testModelCreateDTO.getStringValue());

        return testModel;
    }

}
