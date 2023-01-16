package com.loansharkmss.LoanShark.v1.model;

import com.loansharkmss.LoanShark.v1.config.DebtStatus;

import javax.persistence.*;

@Entity
@Table(name = "debts")
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private Long value;

    @Column(name = "status")
    private DebtStatus debtStatus;

    @ManyToOne
    @JoinColumn(name = "debtor")
    private User debtor;

    public Long getId() {
        return id;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public DebtStatus getDebtStatus() {
        return debtStatus;
    }

    public void setDebtStatus(DebtStatus debtStatus) {
        this.debtStatus = debtStatus;
    }

    public User getDebtor() {
        return debtor;
    }

    public void setDebtor(User debtor) {
        this.debtor = debtor;
    }
}
