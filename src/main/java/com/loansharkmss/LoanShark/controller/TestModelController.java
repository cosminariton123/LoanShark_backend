package com.loansharkmss.LoanShark.controller;

import com.loansharkmss.LoanShark.dtos.TestModelCreateDTO;
import com.loansharkmss.LoanShark.mappers.TestMapper;
import com.loansharkmss.LoanShark.model.TestModel;
import com.loansharkmss.LoanShark.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Validated
@RequestMapping("/test")
public class TestModelController {

    private TestService testService;
    private TestMapper testMapper;

    public TestModelController(TestService testService, TestMapper testMapper) {
        this.testService = testService;
        this.testMapper = testMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestModel> findTestModelById(@PathVariable Integer id) {
        TestModel testModel = testService.findTestModelById(id);
        return ResponseEntity.ok().body(testModel);
    }

    @PostMapping("")
    public ResponseEntity<TestModel> saveNewTestModel(@RequestBody @Valid TestModelCreateDTO testModelCreateDTO) {
        TestModel testModel = testMapper.TestModelCreateDTOToTestModel(testModelCreateDTO);
        TestModel savedTestModel = testService.saveTestModel(testModel);
        return ResponseEntity.created(URI.create("/test/" + savedTestModel.getId())).body(savedTestModel);
    }

    @DeleteMapping("")
    public ResponseEntity<TestModel> deleteTestModel(@PathVariable Integer id) {
        TestModel deletedTestModel = testService.deleteTestModelById(id);
        return ResponseEntity.ok().body(deletedTestModel);
    }
}
