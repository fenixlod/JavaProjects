package com.project.books.data;

import java.util.ArrayList;
import java.util.List;
import com.project.books.models.Book;

public class BooksDataStorageInMemory implements IBooksDataStorage {
	private List<Book> booksDB;
	
	public BooksDataStorageInMemory() {
		booksDB = new ArrayList<Book>();
		
		for(int i = 0;i < 10;i++)
		{
			Book newBook = new Book();
			newBook.setName("Book name " + (i+1));
			newBook.setId(i+1);
			booksDB.add(newBook);
		}
	}

	@Override
	public List<Book> getAllBooks() {
		return booksDB;
	}
}
