package com.example.criteriaquery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.criteriaquery.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>, 
									JpaSpecificationExecutor<Book> {

}
