package com.project.books.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.books.data.IBooksDataStorage;
import com.project.books.models.Book;

@Service
public class BooksService implements IBooksService{

	IBooksDataStorage dataStorage;
	
	@Autowired
	public BooksService(IBooksDataStorage storage) {
		dataStorage = storage;
	}
	
	@Override
	public List<Book> getAllBooks() {
		return dataStorage.getAllBooks();
	}

	@Override
	public Book getById(int id) {
		return dataStorage.getBookById(id);
	}

	@Override
	public List<Book> getByName(String name) {
		return dataStorage.getBooksByName(name);
	}

	@Override
	public boolean addBook(Book newBook) {
		List<Book> booksWithSameName;
		booksWithSameName = dataStorage.getBooksByName(newBook.getName())
			.stream()
			.filter(p -> p.getAutor().equalsIgnoreCase(newBook.getAutor()))
			.collect(Collectors.toList());
		
		if(booksWithSameName == null || booksWithSameName.size() == 0) {
			dataStorage.addBook(newBook);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean updateBook(int id, Book updateBook) {
		List<Book> booksWithSameName;
		Book foundBook = dataStorage.getBookById(id);
		
		if(foundBook == null)
			return false;
		
		booksWithSameName = dataStorage.getBooksByName(updateBook.getName())
				.stream()
				.filter(p -> p.getAutor().equalsIgnoreCase(updateBook.getAutor()))
				.collect(Collectors.toList());
		
		if(booksWithSameName == null || booksWithSameName.size() == 0) {
			updateBook.setId(id);
			dataStorage.updateBook(updateBook);
			return true;
		}
		
		return false;
	}

	@Override
	public boolean deleteBook(int id) {
		return dataStorage.deleteBook(id);
	}
}
