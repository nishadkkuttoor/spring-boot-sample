package com.javatechie.spring.cashe.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.javatechie.spring.cashe.api.model.Book;
import com.javatechie.spring.cashe.api.model.BookReq;
import com.javatechie.spring.cashe.api.model.BookRes;
import com.javatechie.spring.cashe.api.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService Service;
//	add book
	@PostMapping("/addBook")
	public ResponseEntity<Book> saveBook(@RequestBody BookReq book) {
		Book res = Service.saveBook(book);
		return new ResponseEntity<Book>(res,HttpStatus.OK);
		
	}
//	update book by id
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable(value = "id")String Id, @RequestBody Book book) {
		Book res = Service.updateBook(Id,book);
		return new ResponseEntity<Book>(res,HttpStatus.OK);
		
	}
	
////	sort by price
//	@GetMapping("/findAllBooks")
//	public ResponseEntity<List<Book>>sortByPrice(@RequestParam(name = "isSort", required = false) Integer isSort){
//		
//		if(isSort!=null && isSort==1) {
//			System.out.println("sortByPriceByAsc");
//			return new ResponseEntity<List<Book>>(Service.sortByPriceByAsc(),HttpStatus.ACCEPTED); 
//		}
//		 if(isSort!=null && isSort==2) {
//			System.out.println("sortByPriceByDesc");
//			return new ResponseEntity<List<Book>>(Service.sortByPriceByDesc(),HttpStatus.ACCEPTED);
//		}
//		 
//		else {
//			return new ResponseEntity<List<Book>>(Service.findAll(),HttpStatus.ACCEPTED); 
//		}
//		
//	}
	
//	pagination
	@GetMapping("/findAllBooks/{index}/{pageSize}")
	public ResponseEntity<BookRes>sortByPrice(@PathVariable(name = "index") int index,
			@PathVariable(name = "pageSize") int pageSize,@RequestParam(name = "isSort", required = false) Integer isSort){
		
		if(isSort!=null && isSort==1) {
			System.out.println("sortByPriceByAsc");
			return new ResponseEntity<BookRes>(Service.sortByPriceByAsc(index,pageSize),HttpStatus.ACCEPTED); 
		}
		 if(isSort!=null && isSort==2) {
			System.out.println("sortByPriceByDesc");
			return new ResponseEntity<BookRes>(Service.sortByPriceByDesc(index,pageSize),HttpStatus.ACCEPTED);
		}
		 
		else {
			return new ResponseEntity<BookRes>(Service.findAll(index,pageSize),HttpStatus.ACCEPTED); 
		}
		
	}
////	get all books
//	@GetMapping("/findAllBooks")
//	public ResponseEntity<List<Book>>findAll(){
//		return new ResponseEntity<List<Book>>(Service.findAll(),HttpStatus.ACCEPTED); 	
//	}
//	get books by id
	@GetMapping("/findAllBooks/{id}")
	public Book getEmployee(@PathVariable("id") String id) {
	   return Service.getEmployeeById(id);
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") String id){
		Service.deleteById(id);
		return new ResponseEntity<String>("one item deleted",HttpStatus.ACCEPTED);
	}
}
