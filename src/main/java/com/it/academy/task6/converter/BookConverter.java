package com.it.academy.task6.converter;

import com.it.academy.task6.dto.BookDto;
import com.it.academy.task6.entity.Book;

public class BookConverter {

    public static BookDto convertToDto(final Book book, final String authorName, final String genreName) {
        return BookDto.builder()
                .name(book.getName())
                .genre(genreName)
                .author(authorName)
                .creationYear(book.getCreationYear())
                .build();
    }

}
