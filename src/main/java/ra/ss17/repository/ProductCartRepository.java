package ra.ss17.repository;

import ra.ss17.entity.ProductCart;

public interface ProductCartRepository {
    ProductCart findByCustomerAndProduct(int customerId, int productId);
    void save(ProductCart productCart);
    void update(ProductCart productCart);
}
