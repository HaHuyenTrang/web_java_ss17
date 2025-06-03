package ra.ss17.service;

import ra.ss17.entity.ProductCart;

import java.util.List;

public interface ProductCartService {

    ProductCart findByCustomerAndProduct(int customerId, int productId);

    void save(ProductCart productCart);

    void update(ProductCart productCart);

    List<ProductCart> findByCustomerId(int customerId);

    void delete(ProductCart productCart);

    void addToCart(int customerId, int productId, int quantity);

}
