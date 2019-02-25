package com.it.academy.task6.service;

import com.it.academy.task6.dao.author.AuthorDao;
import com.it.academy.task6.dao.book.BookDao;
import com.it.academy.task6.dao.genre.GenreDao;
import com.it.academy.task6.dto.BookDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.it.academy.task6.converter.BookConverter.convertToDto;


@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public List<BookDto> getBooks(List<String> authorNames, List<String> genres, int fromYear, int tillYear) {
        List<BookDto> books = new ArrayList<>();
        bookDao.getBooks(authorNames, genres, fromYear, tillYear)
                .forEach(book -> {
                    Optional<String> authorName = authorDao.findAuthorName(book.getAuthorId());
                    Optional<String> genreName = genreDao.findGenreName(book.getGenreId());
                    books.add(convertToDto(book, authorName.get(), genreName.get()));
                });
        return books;
    }
}
