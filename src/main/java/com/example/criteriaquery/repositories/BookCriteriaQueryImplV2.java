package com.example.criteriaquery.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.criteriaquery.model.Book;

@Repository
public class BookCriteriaQueryImplV2 implements BookRepositoryCustom {

	@Autowired
    EntityManager em;

    @Override
    public List<Book> findBooksByAuthorNameAndTitle(String authorName, String title) {
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);

        Root<Book> book = cq.from(Book.class);
        List<Predicate> predicates = new ArrayList<>();
        
        if (authorName != null) {
            predicates.add(cb.equal(book.get("author"), authorName));
        }
        if (title != null) {
            predicates.add(cb.like(book.get("title"), "%" + title + "%"));
        }
        
        predicates.add(cb.greaterThanOrEqualTo(book.get("fechaLanzamiento"), new Date()));
        
        cq.where(predicates.toArray(new Predicate[0]));

        
        // select b from Book where 
        // AND  title like %:title%
        //AND b.fechaLanzamiento >= curDate()
        return em.createQuery(cq).getResultList();
    }

}
