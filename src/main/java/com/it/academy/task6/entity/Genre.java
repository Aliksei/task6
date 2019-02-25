package com.it.academy.task6.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Genre {

    private Integer id;

    private String name;
}
