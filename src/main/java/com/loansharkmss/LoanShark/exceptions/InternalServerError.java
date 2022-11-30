package com.loansharkmss.LoanShark.exceptions;

public class InternalServerError extends RuntimeException{
    public InternalServerError(String message){
        super(message);
    }
}
