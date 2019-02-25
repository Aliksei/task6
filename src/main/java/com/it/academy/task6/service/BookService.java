package com.it.academy.task6.service;

import com.it.academy.task6.dto.BookDto;
import java.util.List;

public interface BookService {

    List<BookDto> getBooks(List<String> names, List<String> genries, int fromYear, int tillYear);
}
