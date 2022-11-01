package com.javatechie.spring.cashe.api.model;



import javax.validation.constraints.NotBlank;

public class BookReq {
	@NotBlank(message = "name cannot be null")
	private String name;
	private int price;
	private String author;
	private int publishedYear;
	public BookReq(@NotBlank(message = "name cannot be null") String name, int price, String author,
			int publishedYear) {
		super();
		this.name = name;
		this.price = price;
		this.author = author;
		this.publishedYear = publishedYear;
	}
	public BookReq() {
		super();
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "BookReq [name=" + name + ", price=" + price + ", author=" + author + ", publishedYear=" + publishedYear
				+ "]";
	}
	
	
}
