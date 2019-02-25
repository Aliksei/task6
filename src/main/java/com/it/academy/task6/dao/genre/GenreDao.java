package com.it.academy.task6.dao.genre;

import java.util.Optional;

public interface GenreDao {

    Optional<String> findGenreName(final Integer id);
}
