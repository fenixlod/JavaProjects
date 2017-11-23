package com.project.books.data;

import java.util.List;

import com.project.books.models.Book;

public interface IBooksDataStorage {

	List<Book> getAllBooks();
	Book getBookById(int id);
	List<Book> getBooksByName(String name);
	void addBook(Book newBook);
	void updateBook(Book updateBook);
	boolean deleteBook(int id);
}
