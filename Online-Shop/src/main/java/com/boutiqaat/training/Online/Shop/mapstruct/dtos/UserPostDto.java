package com.boutiqaat.training.Online.Shop.mapstruct.dtos;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostDto {
	
    @JsonProperty("id")
    private int id;

    @Email
    @NotNull
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("password")
    private String password;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;
}