package com.loansharkmss.LoanShark.v1.dtos;

public class UserMinimalCardNoImage {

    private final Long id;

    private final String username;

    private final String firstName;

    private final String lastName;

    public UserMinimalCardNoImage(Long id, String username, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
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
}
