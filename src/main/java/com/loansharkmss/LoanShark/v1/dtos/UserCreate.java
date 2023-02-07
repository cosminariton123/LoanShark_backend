package com.loansharkmss.LoanShark.v1.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserCreate {

    @NotNull(message = "email must not be null")
    @NotBlank(message = "email must not be blank")
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+=?^_`{|}~-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-]+$", message = "email does not have a valid structure")
    private final String email;

    @NotNull(message = "username must not be null")
    @NotBlank(message = "username must not be blank")
    private final String username;

    @NotNull(message = "password must not be null")
    @NotBlank(message = "password must not blank")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must contain minimum eight characters, " +
                                                                                    "at least one uppercase letter, one lowercase letter and one number")
    private final String password;

    @NotNull(message = "firstName must not be null")
    @NotBlank(message = "firstName must not be blank")
    private final String firstName;

    @NotNull(message = "lastName must not be null")
    @NotBlank(message = "lastName must not be blank")
    private final String lastName;

    @NotNull(message = "description must not be null(can be blank).")
    private final String description;

    public UserCreate(String email, String username, String password, String firstName, String lastName, String description) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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
}
