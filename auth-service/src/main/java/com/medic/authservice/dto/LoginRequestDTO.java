package com.medic.authservice.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequestDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be a valid email request")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(message = "Password should be at least 8 characters long", min = 8)
    private String password;

    public @NotBlank(message = "Email is required") @Email(message = "Email should be a valid email request") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be a valid email request") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is required") @Size(message = "Password should be at least 8 characters long", min = 8) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(message = "Password should be at least 8 characters long", min = 8) String password) {
        this.password = password;
    }
}
