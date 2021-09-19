package com.boutiqaat.training.Online.Shop.mapstruct.dtos;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("releaseDate")
    private Date releaseDate;

    @JsonProperty("authors")
    private Set<AuthorDto> authors;
}