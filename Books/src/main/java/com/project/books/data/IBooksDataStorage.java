package com.project.books.data;

import java.util.List;

import com.project.books.models.Book;

public interface IBooksDataStorage {

	List<Book> getAllBooks();
}
