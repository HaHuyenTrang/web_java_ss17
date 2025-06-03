package ra.ss17.repository;


import ra.ss17.entity.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAll();

    void delete(int id);
}