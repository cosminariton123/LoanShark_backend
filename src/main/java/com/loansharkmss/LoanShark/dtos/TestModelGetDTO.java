package com.loansharkmss.LoanShark.dtos;

import javax.validation.constraints.*;

public class TestModelGetDTO {

    @NotNull(message = "numericalValue must not be null")
    @Min(value = 0, message = "numericalValue must be greater than 0")
    @Max(value = 999, message = "numericalValue must be lower than 999")
    private Integer numericalValue;

    @NotNull(message = "stringValue must not be null")
    @NotBlank(message = "stringValue must not be blank")
    @Pattern(regexp = "aaa.*aaa", message = "stringValue must should comply with regex aaa.*aaa")
    private String stringValue;

    public Integer getNumericalValue() {
        return numericalValue;
    }

    public void setNumericalValue(Integer numericalValue) {
        this.numericalValue = numericalValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
}
