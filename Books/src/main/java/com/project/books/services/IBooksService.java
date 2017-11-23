package com.project.books.services;

import java.util.List;
import com.project.books.models.Book;

public interface IBooksService {
	
	List<Book> getAllBooks();
	Book getById(int id);
	List<Book> getByName(String name);
	boolean addBook(Book newBook);
	boolean updateBook(int id, Book updateBook);
	boolean deleteBook(int id);
}
