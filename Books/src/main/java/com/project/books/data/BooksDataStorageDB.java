package com.project.books.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.project.books.models.Book;

public class BooksDataStorageDB implements IBooksDataStorage {

	private JdbcTemplate dbTemplate;
	
	@Autowired
	public BooksDataStorageDB(DataSource source) {
		dbTemplate = new JdbcTemplate(source);
	}
	
	@Override
	public List<Book> getAllBooks() {
		try {
			String sql = "SELECT * FROM books";
			List<Book> allBooks = dbTemplate.query(sql, new BookDBRowMapper());
			return allBooks;
		}
		catch(DataAccessException e) {
			return null;
		}
	}

	@Override
	public Book getBookById(int id) {
		try {
			String sqlQuery = "SELECT * FROM books WHERE id=" + id;
			Book foundBook = dbTemplate.query(sqlQuery, new BookDBExtractor());
			return foundBook;
		}
		catch(DataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Book> getBooksByName(String name) {
		try {
			String sqlQuery = "SELECT * FROM books WHERE name=" + name;
			System.out.println(sqlQuery);
			List<Book> foundBooks = dbTemplate.query(sqlQuery, new RowMapper<Book>() {

				@Override
				public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
					System.out.println("BOOK FOUND!");
					Book aBook = new Book();
					aBook.setId(rs.getInt("id"));
					aBook.setName(rs.getString("name"));
					aBook.setAutor(rs.getString("author"));
					return aBook;
				}
			});
			
			if(foundBooks != null)
				return foundBooks;
			else
				return new ArrayList<Book>();
		}
		catch(DataAccessException e) {
			return new ArrayList<Book>();
		}
	}

	@Override
	public void addBook(Book newBook) {
		String sql = "INSERT INTO books (id,name,author) VALUES(?,?,?)";
		dbTemplate.update(sql,newBook.getId(),newBook.getName(),newBook.getAutor());
	}

	@Override
	public void updateBook(Book updateBook) {
		String sql = "UPDATE books SET name=?, author=? WHERE id=?";
		dbTemplate.update(sql,updateBook.getName(),updateBook.getAutor(),updateBook.getId());
	}

	@Override
	public boolean deleteBook(int id) {
		String sql = "DELETE FROM books WHERE id=?";
		dbTemplate.update(sql,id);
		return true;
	}
}
