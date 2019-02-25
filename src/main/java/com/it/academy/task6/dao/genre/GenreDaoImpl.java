package com.it.academy.task6.dao.genre;

import com.it.academy.task6.entity.Genre;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import static java.util.Optional.ofNullable;

@Repository
public class GenreDaoImpl implements GenreDao {

    private static final String FIND_NAME = "SELECT * FROM library.genre where id = :id ";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public GenreDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<String> findGenreName(final Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        Genre genre = jdbcTemplate.queryForObject(FIND_NAME,
                params, new GenreRowMapper());

        Optional<String> name;
        if (genre != null) {
            name = ofNullable(genre.getName());
        } else {
            name = Optional.empty();
        }
        return name;
    }

    private final class GenreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int i) throws SQLException {
            return Genre.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .build();
        }
    }
}
