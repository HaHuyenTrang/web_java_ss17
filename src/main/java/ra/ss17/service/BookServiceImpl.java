package ra.ss17.service;

import org.springframework.stereotype.Service;
import ra.ss17.entity.Book;
import ra.ss17.repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepo;

    public BookServiceImpl(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> getAll() {
        return bookRepo.getAll();
    }

    @Override
    public void delete(int id) {
        bookRepo.delete(id);
    }
}