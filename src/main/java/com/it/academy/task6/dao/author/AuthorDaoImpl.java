package com.it.academy.task6.dao.author;

import com.it.academy.task6.entity.Author;
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
public class AuthorDaoImpl implements AuthorDao {

    private static final String FIND_NAME_BY_ID = "SELECT * FROM library.author where id = :id";
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<String> findAuthorName(final Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        Author author = jdbcTemplate.queryForObject(FIND_NAME_BY_ID,
                params, new AuthorRowMapper());

        Optional<String> name;
        if (author != null) {
            name = ofNullable(author.getName());
        } else {
            name = Optional.empty();
        }
        return name;
    }

    private final class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            return Author.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .dateOfBirth(rs.getDate("date_of_birth").toLocalDate())
                    .build();
        }
    }
}
