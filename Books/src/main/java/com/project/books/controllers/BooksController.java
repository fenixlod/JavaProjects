package com.project.books.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.project.books.models.Book;
import com.project.books.services.IBooksService;

@RestController
public class BooksController {
	private IBooksService bookService;
	
	@Autowired
	public BooksController(IBooksService service) {
		bookService = service;
	}
	@RequestMapping(value= "/books",method=RequestMethod.GET)
	public ResponseEntity<?> getAllBooks() {
		List<Book> allBooks = bookService.getAllBooks();
		
		if(allBooks == null || allBooks.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Book>>(allBooks,HttpStatus.OK);
	}
}
