package com.library_management_thymeleaf_CRUD.inventory_management.service;

import com.library_management_thymeleaf_CRUD.inventory_management.entity.Book;
import com.library_management_thymeleaf_CRUD.inventory_management.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {

        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return bookRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Book with id: " +id+ " not found")
        );
    }

    @Override
    public Book save(Book book) {
        if(book == null){
            throw new RuntimeException("Book cannot be null");
        }

        if(book.getTitle()==null || book.getTitle().isBlank() ||
                book.getAuthor()==null || book.getAuthor().isBlank() ){
            throw new RuntimeException("Book title/author field cannot be empty");
        }
        Book existingBook = findByTitleAndAuthor(book.getTitle(),book.getAuthor());

        if(existingBook==null){
            return bookRepository.save(book);
        }
        if(existingBook.getId().equals(book.getId())){
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("Book already exists");
        }

    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        bookRepository.deleteById(id);
    }

    @Override
    public boolean existsByTitleAndAuthor(String title, String author) {
        return  bookRepository.existsByTitleAndAuthor(title,author);
    }

    @Override
    public List<Book> findByCategory(String category) {
        if(category==null || category.isBlank()){
            throw new RuntimeException("Category field cannot be empty");
        }
        return bookRepository.findByCategory(category);
    }

    @Override
    public List<Book> findByAuthor(String author) {

        if(author==null || author.isBlank()){
            throw new RuntimeException("Author field cannot be null or blank");
        }
        return bookRepository.findByAuthorContainingIgnoreCase(author);

    }

    @Override
    public List<Book> findByTitle(String title) {

        if(title==null || title.isBlank()){
            throw new RuntimeException("Title cannot be null or blank");
        }
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public Book findByTitleAndAuthor(String title, String author) {
        if(title==null || title.isBlank() || author==null || author.isBlank()){
            throw new RuntimeException("Title and Author cannot be null or blank");
        }
        return bookRepository.findByTitleAndAuthor(title,author);
    }
}
