package com.project.books.services;

import java.util.List;

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

}
