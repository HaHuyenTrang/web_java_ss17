package ra.ss17.service;


import ra.ss17.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    void delete(int id);

}