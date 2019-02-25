package com.it.academy.task6.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {

    private String name;

    private int creationYear;

    private String genre;

    private String author;
}
