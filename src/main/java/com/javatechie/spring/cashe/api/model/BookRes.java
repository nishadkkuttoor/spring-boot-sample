package com.javatechie.spring.cashe.api.model;


import java.util.List;

public class BookRes {
	List<Book> Body;
	long length;
	public BookRes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookRes(List<Book> body, long length) {
		super();
		Body = body;
		this.length = length;
	}
	public List<Book> getBody() {
		return Body;
	}
	public void setBody(List<Book> body) {
		Body = body;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	@Override
	public String toString() {
		return "BookRes [Body=" + Body + ", length=" + length + "]";
	}
	
	
	
}
