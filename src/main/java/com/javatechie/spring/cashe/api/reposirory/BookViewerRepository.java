package com.javatechie.spring.cashe.api.reposirory;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.javatechie.spring.cashe.api.model.BookRes;

public interface BookViewerRepository  extends MongoRepository<BookRes, String>{

}
