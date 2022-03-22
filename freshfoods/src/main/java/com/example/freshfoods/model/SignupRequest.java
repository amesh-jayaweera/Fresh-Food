package com.example.freshfoods.model;

import com.example.freshfoods.annotation.StrongPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequest {

    @NotBlank(message = "{NotEmpty.username}")
    @Size(min = 6, max = 20, message = "{Size.username}")
    private String username;

    @NotBlank(message = "{NotEmpty.name}")
    private String name;
    
    @NotBlank(message = "{NotEmpty.password}")
    @StrongPassword(message = "{Strong.password}")
    private String password;
}
