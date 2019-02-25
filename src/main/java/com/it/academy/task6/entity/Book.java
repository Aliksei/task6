package com.it.academy.task6.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {

    private Integer id;

    private String name;

    private Integer creationYear;

    private Integer genreId;

    private Integer authorId;

}
