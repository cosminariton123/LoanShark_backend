package com.loansharkmss.LoanShark.config;

import javax.persistence.*;

@Entity
@Table
public class DefaultConfigFlag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

}
