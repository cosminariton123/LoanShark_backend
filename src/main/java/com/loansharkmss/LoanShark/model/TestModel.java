package com.loansharkmss.LoanShark.model;

import javax.persistence.*;

@Entity
@Table
public class TestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numerical_value")
    private Integer numericalValue;

    @Column(name = "string_value")
    private String stringValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
