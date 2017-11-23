package com.project.books.data;

import java.util.ArrayList;
import java.util.List;
import com.project.books.models.Book;

public class BooksDataStorageInMemory implements IBooksDataStorage {
	private List<Book> booksDB;
	private int nextFreeId;
	
	public BooksDataStorageInMemory() {
		booksDB = new ArrayList<Book>();
		nextFreeId = 0;
		
		for(int i = 0;i < 10;i++)
		{
			Book newBook = new Book();
			newBook.setName("Book name " + ((i%5)+1));
			newBook.setAutor("Autor " + (i+1));
			newBook.setId(++nextFreeId);
			booksDB.add(newBook);
		}
	}

	@Override
	public List<Book> getAllBooks() {
		return booksDB;
	}

	@Override
	public Book getBookById(int id) {
		
		Book foundBook = null;
		for(Book book : booksDB) {
			if(book.getId() == id) {
				foundBook = book;
				break;
			}
		}
		return foundBook;
	}

	@Override
	public List<Book> getBooksByName(String name) {
		List<Book> foundBooks = new ArrayList<Book>();
		
		for(Book book : booksDB) {
			if(book.getName().equalsIgnoreCase(name)) {
				foundBooks.add(book);
			}
		}
		return foundBooks;
	}

	@Override
	public void addBook(Book newBook) {
		newBook.setId(++nextFreeId);
		booksDB.add(newBook);
	}

	@Override
	public void updateBook(Book updateBook) {
		for(int i = 0;i < booksDB.size();i++) {
			if(booksDB.get(i).getId() == updateBook.getId()) {
				booksDB.set(i, updateBook);
				break;
			}
		}
	}

	@Override
	public boolean deleteBook(int id) {
		for(int i = 0;i < booksDB.size();i++) {
			if(booksDB.get(i).getId() == id) {
				booksDB.remove(i);
				return true;
			}
		}
		return false;
	}
}
