package com.example.criteriaquery.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.criteriaquery.model.Book;
import com.example.criteriaquery.services.BookService;

@RestController
public class BookController {

	@Autowired
	BookService bookService;
	
	@GetMapping("findAll")
	public List<Book> findAllBooks(@RequestParam(required = false) String author, @RequestParam(required = false) String title){
		return bookService.findBooksByAuthorNameAndTitle(author, title);
	}
	
	@GetMapping("findAllByAuthor/{author}")
	public List<Book> findAllByAuthor(@PathVariable String author){
		return bookService.findBooksByAuthorName(author);
	}
	
	@GetMapping("findAllByTitle/{title}")
	public List<Book> findAllByTitle(@PathVariable String title){
		return bookService.findBooksByTitle(title);
	}
}
