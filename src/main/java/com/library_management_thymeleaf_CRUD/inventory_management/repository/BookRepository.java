package com.library_management_thymeleaf_CRUD.inventory_management.repository;

import com.library_management_thymeleaf_CRUD.inventory_management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByCategory(String category);
    boolean existsByTitleAndAuthor(String title,String author);

}
