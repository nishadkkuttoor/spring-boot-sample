package com.javatechie.spring.cashe.api.model;


import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;




public class Book {
	@Id 
	private String id;
	private String name;
	private int price;
	private String author;
	private int publishedYear;
	private LocalDateTime createdAt ;
	private LocalDateTime updatedAt;
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", author=" + author + ", publishedYear="
				+ publishedYear + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	public Book(String id, @NotBlank(message = "Name required") String name,
			@NotNull(message = "Price cannot be null") int price,
			@NotBlank(message = "author name required") String author,
			@NotBlank(message = "publishedYear required") int publishedYear, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.author = author;
		this.publishedYear = publishedYear;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPublishedYear() {
		return publishedYear;
	}
	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
	
}

