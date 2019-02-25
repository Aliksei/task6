package com.it.academy.task6.controller.rest;


import com.it.academy.task6.CsvUtils;
import com.it.academy.task6.dto.BookDto;
import com.it.academy.task6.service.BookService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/books/download",
            params = {"authorNames", "genres", "fromYear", "tillYear"},
            produces = "text/csv")
    public void getBooks(@RequestParam(value = "authorNames", required = false) List<String> authorNames,
                         @RequestParam(value = "genres", required = false) List<String> genres,
                         @RequestParam(value = "fromYear", required = false) Integer fromYear,
                         @RequestParam(value = "tillYear", required = false) Integer tillYear,
                         HttpServletResponse response) throws IOException {
        if (authorNames == null && genres == null && (fromYear == null || tillYear == null)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "At least one parameter should be passed");
        } else {
            List<BookDto> books = bookService.getBooks(authorNames, genres, fromYear, tillYear);
            response.addHeader("Content-disposition", "attachment;filename=books.csv");
            CsvUtils.writeFile(books, response);
            response.flushBuffer();
        }
    }

}
