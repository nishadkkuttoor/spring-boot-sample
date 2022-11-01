package com.javatechie.spring.cashe.api.reposirory;


import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.javatechie.spring.cashe.api.model.Book;


public interface BookRepository extends MongoRepository<Book, String> {
	@Query(value = "{}")
	public List<Book> getBooksByID(PageRequest pageRequest,Sort sort);
	
	@Query(value = "{}")
	public List<Book> findAllBooks(PageRequest pageRequest);
	
	@Query(count = true, value = "{}")
	public long countBooks();
}
