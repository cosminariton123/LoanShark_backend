package com.loansharkmss.LoanShark.v1.dtos;


import javax.validation.constraints.NotNull;

public class DebtCreate {

    @NotNull(message = "value must not be null")
    private Double value;

    @NotNull(message = "debtorId must not be null")
    private Long debtorId;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getDebtorId() {
        return debtorId;
    }

    public void setDebtorId(Long debtorId) {
        this.debtorId = debtorId;
    }
}
