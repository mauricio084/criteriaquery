package com.example.criteriaquery.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.criteriaquery.model.Book;
import com.example.criteriaquery.repositories.BookCriteriaQueryImplV2;
import com.example.criteriaquery.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookCriteriaQueryImplV2 criteriaQueryImpl;
	
	@Override
	public List<Book> findBooksByAuthorName(String author){
		return bookRepository.findAll(hasAuthor(author));
	}
	
	@Override
	public List<Book> findBooksByTitle(String title){
		return bookRepository.findAll(titleContains(title));
	}
	
	public List<Book> findBooksByTitleOrAuthor(String title, String author){
		return bookRepository.findAll(titleContains(title).or(hasAuthor(author)));
	}
	
	@Override
	public List<Book> findBooksByAuthorNameAndTitle(String author, String title){
		return criteriaQueryImpl.findBooksByAuthorNameAndTitle(author, title);
	}
	
	private Specification<Book> hasAuthor(String author) {
		return (book, cq, cb) -> cb.like(book.get("author"), "%"+author+"%");
	}
	
	private Specification<Book> fechaLanzamiento(Date fecha) {
		return (book, cq, cb) -> cb.greaterThanOrEqualTo(book.get("fechaLanzamiento"), fecha);
	}

	private Specification<Book> titleContains(String title) {
	    return (book, cq, cb) -> cb.like(book.get("title"), "%" + title + "%");
	}
	
	void addSpecification(Specification<Book> source, Specification<Book> other){
		
	}
	
}
