package com.javatechie.spring.cashe.api.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.javatechie.spring.cashe.api.model.Book;
import com.javatechie.spring.cashe.api.model.BookReq;
import com.javatechie.spring.cashe.api.model.BookRes;
import com.javatechie.spring.cashe.api.reposirory.BookRepository;

@Service
public class BookService {
	@Autowired
	private SequanceGenerator sequanceGenerator;
	@Autowired
	private BookRepository repository;

	public Book saveBook(BookReq book) {
		String name = book.getName();
		int price = book.getPrice();
		String auther = book.getAuthor();
		int publisedyear = book.getPublishedYear();
		Book book1 = new Book(sequanceGenerator.nextId(1L), name, price, auther, publisedyear, LocalDateTime.now(),
				null);
		return repository.save(book1);
	}

	public Book updateBook(String id, Book book) {
		Optional<Book> getById = repository.findById(id);

		if (getById.isPresent()) {
			Book existingBook = getById.get();
			existingBook.setName(book.getName());
			existingBook.setPrice(book.getPrice());
			existingBook.setAuthor(book.getAuthor());
			existingBook.setPublishedYear(book.getPublishedYear());
			existingBook.setUpdatedAt(LocalDateTime.now());
//			
			return repository.save(existingBook);

		} else {
			System.out.println("no book response in this id ");
			return null;
		}

	}
	
	public BookRes sortByPriceByAsc(int index, int pageSize) {
		Sort sort = Sort.by(Direction.ASC, "price");
		
		List<Book> findAll = new ArrayList<Book>();
		long length = 0;
			findAll = repository.getBooksByID(PageRequest.of(index, pageSize),sort);
			length = repository.countBooks();
		
			BookRes	res	=new BookRes(findAll, length);
		
			
		return res;
//		return repository.getBooksByID(sort);
	}
	
	public BookRes sortByPriceByDesc(int index, int pageSize) {
		Sort sort = Sort.by(Direction.DESC, "price");
		
		List<Book> findAll = new ArrayList<Book>();
		long length = 0;
			findAll = repository.getBooksByID(PageRequest.of(index, pageSize),sort);
			length = repository.countBooks();
		
			BookRes	res	=new BookRes(findAll, length);
	
			
		return res;
//
	}

	public BookRes findAll(int index, int pageSize) {
	
		List<Book> findAll = new ArrayList<Book>();
		long length = 0;
			findAll = repository.findAllBooks(PageRequest.of(index, pageSize));
			length = repository.countBooks();
		
			BookRes	res	=new BookRes(findAll, length);
//			res.setBody(findAll);
//			res.setLength(length);
//			
		return res;
	}

	public Book getEmployeeById(String id) {
		return repository.findById(id).get();
	}

	public void deleteAll() {
		repository.deleteAll();
	}

	public void deleteById(String id) {
		repository.deleteById(id);
	}

}
