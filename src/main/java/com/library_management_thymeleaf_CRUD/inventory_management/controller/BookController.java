package com.library_management_thymeleaf_CRUD.inventory_management.controller;
import com.library_management_thymeleaf_CRUD.inventory_management.entity.Book;
import com.library_management_thymeleaf_CRUD.inventory_management.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/list")
    public String showList(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping("/saveBook")
    public String saveBook(@Valid @ModelAttribute Book book, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "book-form";
        }

            bookService.save(book);
        redirectAttributes.addFlashAttribute("success", "Book saved successfully");
        return "redirect:/books/list";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam Long id) {
        bookService.deleteById(id);
        return "redirect:/books/list";
    }


    @GetMapping("/showFormForUpdate")
        public String showFormForUpdate(Model model, @RequestParam long id) {

        model.addAttribute("book", bookService.findById(id));
        return "book-form";
    }

    @GetMapping("/search")
    public String searchBooks(@RequestParam String query, Model model) {

        if(query!=null && !query.isBlank()){
            model.addAttribute("books", bookService.findByTitle(query));
            model.addAttribute("query", query);
            return "book-list";

        }
        model.addAttribute("error","Please enter a valid book title.");
        model.addAttribute("books", bookService.findAll());

        return "book-list";
    }


}
