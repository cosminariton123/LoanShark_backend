package com.loansharkmss.LoanShark.v1.dtos;

public class UserMinimalCard {

    private final Long id;

    private final String username;

    private final String firstName;

    private final String lastName;

    private final ImageCard image;

    public UserMinimalCard(Long id, String username, String firstName, String lastName, ImageCard image) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
    }

    public ImageCard getImage() {
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
