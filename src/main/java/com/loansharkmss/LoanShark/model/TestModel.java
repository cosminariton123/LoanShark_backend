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
}
