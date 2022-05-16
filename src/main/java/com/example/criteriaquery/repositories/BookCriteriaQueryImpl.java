package com.example.criteriaquery.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.criteriaquery.model.Book;

@Repository
public class BookCriteriaQueryImpl implements BookRepositoryCustom  {

	@Autowired
	private EntityManager em;

	@Override
	public List<Book> findBooksByAuthorNameAndTitle(String authorName, String title) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Book> cq = cb.createQuery(Book.class);
		Root<Book> book = cq.from(Book.class);
		
		Predicate authorNamePredicate = null;
		
		if (authorName != null) {
			authorNamePredicate = cb.equal(book.get("author"), authorName);
		}
		Predicate titlePredicate = cb.like(book.get("title"), "%" + title + "%");
		Predicate lanzamientoPredicate = cb.greaterThan(book.get("fechaLanzamiento"), new Date());
		
		Predicate predicadoOr = cb.or(authorNamePredicate, titlePredicate);
		Predicate predicadoAnd = cb.and(predicadoOr, lanzamientoPredicate);
		
		// SELECT * from Book where (author = ?1 and title like %?2%) or  fechaLanzamiento >= ?3
		cq.where(predicadoAnd);

		TypedQuery<Book> query = em.createQuery(cq);
		return query.getResultList();
	}

}
