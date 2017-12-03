package com.project.books.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.books.models.Book;
import com.project.books.services.IBooksService;

@RestController
@RequestMapping("/api")
public class BooksController {
	private IBooksService bookService;
	
	@Autowired
	public BooksController(IBooksService service) {
		bookService = service;
	}
	
	//Get all books
	@RequestMapping(value= "/books",method=RequestMethod.GET)
	public ResponseEntity<?> getAllBooks() {
		List<Book> allBooks = bookService.getAllBooks();
		
		if(allBooks == null || allBooks.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Book>>(allBooks,HttpStatus.OK);
	}
	
	//Get book by id
	@RequestMapping(value = "/books", params = "id" , method = RequestMethod.GET)
	public ResponseEntity<?> getBookById(@RequestParam(value = "id",defaultValue = "0") int id) {
		Book book = bookService.getById(id);
		
		if(!book.isValid())
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	//Get books by name
	@RequestMapping(value = "/books", params = "name" , method = RequestMethod.GET)
	public ResponseEntity<?> getBooksByName(@RequestParam(value = "name",defaultValue = "none") String name) {
		List<Book> books = bookService.getByName(name);
		
		if(books == null || books.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
	
	//Add book
	@RequestMapping(value = "/books",method = RequestMethod.POST)
	public ResponseEntity<?> addBook(@RequestBody Book newBook) {
		if(bookService.addBook(newBook))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}
	
	//Update book
	@RequestMapping(value = "/books/{id}",method = RequestMethod.PUT)
	public ResponseEntity<?> updateBook(@PathVariable("id") int id, @RequestBody Book updateBook) {
		if(bookService.updateBook(id,updateBook))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}
	
	//Delete book by id
	@RequestMapping(value = "/books/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBook(@PathVariable("id")  int id) {
		if(bookService.deleteBook(id))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
