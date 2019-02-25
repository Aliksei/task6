package com.it.academy.task6.dao.author;

import java.util.Optional;

public interface AuthorDao {
    Optional<String> findAuthorName(Integer id);
}
