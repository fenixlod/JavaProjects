package com.project.books.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.project.books.models.Book;

public class BookDBRowMapper implements RowMapper<Book>{

	@Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Book aBook = new Book();
    	aBook.setId(rs.getInt("id"));
    	aBook.setName(rs.getString("name"));
    	aBook.setAutor(rs.getString("author"));
        return aBook;
	}
}
