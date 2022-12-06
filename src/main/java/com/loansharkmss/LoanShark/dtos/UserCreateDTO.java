package com.loansharkmss.LoanShark.dtos;

import javax.validation.constraints.*;

public class UserCreateDTO {

    @NotNull(message = "email must not be null")
    @NotBlank(message = "email must not be blank")
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+=?^_`{|}~-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-]+$", message = "email does not have a valid structure")
    private String email;

    @NotNull(message = "username must not be null")
    @NotBlank(message = "username must not be blank")
    private String username;

    @NotNull(message = "password must not be null")
    @NotBlank(message = "password must not blank")
    private String password;

    @NotNull(message = "firstName must not be null")
    @NotBlank(message = "firstName must not be blank")
    private String firstName;

    @NotNull(message = "lastName must not be null")
    @NotBlank(message = "lastName must not be blank")
    private String lastName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
