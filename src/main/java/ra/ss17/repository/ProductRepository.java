package ra.ss17.repository;

import ra.ss17.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAll(int page, int size);
    Product findById(int id);
}