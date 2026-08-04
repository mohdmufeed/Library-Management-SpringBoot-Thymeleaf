package com.library_management_thymeleaf_CRUD.inventory_management.service;

import com.library_management_thymeleaf_CRUD.inventory_management.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();
    Book findById(Long id);
    Book save (Book book);
    void deleteById(Long id);

    boolean existsByTitleAndAuthor(String title,String author);

    List<Book> findByCategory(String category);
    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);





}
