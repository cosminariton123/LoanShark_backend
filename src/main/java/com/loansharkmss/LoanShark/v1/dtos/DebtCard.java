package com.loansharkmss.LoanShark.v1.dtos;

import com.loansharkmss.LoanShark.v1.config.DebtStatus;

public class DebtCard {

    private final Long id;

    private final Long memberId;

    private final String username;

    private final String firstName;

    private final String lastName;

    private final String email;

    private final Double value;

    private final DebtStatus debtStatus;

    public DebtCard(Long id, Long memberId, String username, String firstName, String lastName, String email, Double value, DebtStatus debtStatus) {
        this.id = id;
        this.memberId = memberId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.value = value;
        this.debtStatus = debtStatus;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Double getValue() {
        return value;
    }

    public DebtStatus getDebtStatus() {
        return debtStatus;
    }
}
