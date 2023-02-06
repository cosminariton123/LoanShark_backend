package com.loansharkmss.LoanShark.v1.dtos;

public class UserProfileCard {

    private final Long id;

    private final String username;

    private final String firstName;

    private final String lastName;

    private final String description;

    private final ImageCard image;

    public UserProfileCard(Long id, String username, String firstName, String lastName, String description, ImageCard image) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public ImageCard getImage() {
        return image;
    }
}
