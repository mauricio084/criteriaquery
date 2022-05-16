package com.example.criteriaquery.repositories;

import java.util.List;

import com.example.criteriaquery.model.Book;

public interface BookRepositoryCustom {
	List<Book> findBooksByAuthorNameAndTitle(String authorName, String title);
}
