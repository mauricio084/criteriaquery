package com.example.criteriaquery;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.criteriaquery.model.Book;
import com.example.criteriaquery.repositories.BookRepository;

@Configuration
class LoadEquipos {

	@Bean
	CommandLineRunner initUsuarios(BookRepository repository) {
		return args -> {
			repository.save(new Book("Clean Code", "Robert C. Martin"));
			repository.save(new Book("Clean Arquitecture", "Robert C. Martin"));
			repository.save(new Book("Agile Software Development, Principles, Patterns, and Practices", "Robert C. Martin"));
			repository.save(new Book("The Pragmatic Programmer", "Andy Hunt"));
			repository.save(new Book("The Mythical Man-Month", "Frederick Brooks"));
		};
	}
}
