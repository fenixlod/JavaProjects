package com.project.books.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.project.books.models.Book;

public class BookDBExtractor implements ResultSetExtractor<Book> {
	
	 @Override
     public Book extractData(ResultSet rs) throws SQLException, DataAccessException {
		 Book fBook = new Book();
		 if(rs.next()) {
			fBook.setId(rs.getInt("id"));
			fBook.setName(rs.getString("name"));
			fBook.setAutor(rs.getString("author"));
		}
		else {
			fBook.setValid(false);
		}
        return fBook;
     }
}
