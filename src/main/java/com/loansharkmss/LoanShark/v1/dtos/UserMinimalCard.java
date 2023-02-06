package com.loansharkmss.LoanShark.v1.dtos;

import com.loansharkmss.LoanShark.v1.model.Image;

public class UserMinimalCard {

    private final Long id;

    private final String username;

    private final String firstName;

    private final String lastName;

    private final Image image;

    public UserMinimalCard(Long id, String username, String firstName, String lastName, Image image) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
    }

    public Image getImage() {
        return image;
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
