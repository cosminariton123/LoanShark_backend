package com.loansharkmss.LoanShark.v1.dtos;

public class UserLogin {

    private String username_or_email;

    private String password;

    public String getUsername_or_email() {
        return username_or_email;
    }

    public void setUsername_or_email(String username_or_email) {
        this.username_or_email = username_or_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
