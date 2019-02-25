package com.it.academy.task6.dao.book;

import com.it.academy.task6.entity.Book;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.StringJoiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> getBooks(List<String> names, List<String> genres, Integer fromDate, Integer till) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("names", names);
        params.addValue("genres", genres);
        params.addValue("fromDate", fromDate);
        params.addValue("till", till);

        StringJoiner where = new StringJoiner(" AND", " WHERE ", "").setEmptyValue("");
        if (names != null && !names.isEmpty()) {
            where.add(" a.name IN (:names)");
        }
        if (genres != null && !genres.isEmpty()) {
            where.add(" g.name IN (:genres)");
        }
        if (fromDate != null && till != null) {
            where.add(" b.creation_year between :fromDate and :till");
        }
        String query = "SELECT * from library.book b " +
                "left join library.genre g " +
                "on b.genre_id = g.id " +
                "left join library.author a " +
                "on b.author_id = a.id " +
                where;
        return jdbcTemplate.query(query, params, new BookMapper());
    }

    private class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int i) throws SQLException {
            return Book.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .creationYear(rs.getInt("creation_year"))
                    .genreId(rs.getInt("genre_id"))
                    .authorId(rs.getInt("author_id"))
                    .build();
        }
    }
}
