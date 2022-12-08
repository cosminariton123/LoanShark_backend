package com.loansharkmss.LoanShark.v1.exceptions;

public class InternalServerError extends RuntimeException{
    public InternalServerError(String message){
        super(message);
    }
}
