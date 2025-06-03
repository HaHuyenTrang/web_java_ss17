package ra.ss17.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ra.ss17.entity.Book;
import ra.ss17.service.BookService;

import java.util.List;

@Controller
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("book")
    public String getAll(Model model) {
        List<Book> books = bookService.getAll();
        model.addAttribute("books", books);

        return "book_list";
    }

    @GetMapping("book-delete/{id}")
    public String delete(@PathVariable int id) {
        bookService.delete(id);

        return "redirect:/book";
    }
}