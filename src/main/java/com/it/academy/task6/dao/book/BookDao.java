package com.it.academy.task6.dao.book;

import com.it.academy.task6.entity.Book;
import java.util.List;

public interface BookDao {

    List<Book> getBooks(List<String> names, List<String> genries, Integer fromDate, Integer till);

}
