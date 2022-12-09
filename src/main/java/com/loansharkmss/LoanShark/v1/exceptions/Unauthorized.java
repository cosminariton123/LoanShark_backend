package com.loansharkmss.LoanShark.v1.exceptions;

public class Unauthorized extends RuntimeException{

    public Unauthorized(String message){
        super(message);
    }
}
