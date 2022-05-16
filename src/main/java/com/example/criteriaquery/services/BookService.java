package com.example.criteriaquery.services;

import java.util.List;

import com.example.criteriaquery.model.Book;

public interface BookService {

	List<Book> findBooksByAuthorNameAndTitle(String author, String title);

	List<Book> findBooksByTitle(String title);

	List<Book> findBooksByAuthorName(String author);

}
