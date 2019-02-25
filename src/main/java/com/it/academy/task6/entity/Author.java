package com.it.academy.task6.entity;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Author {

    private Integer id;

    private String name;

    private LocalDate dateOfBirth;
}
