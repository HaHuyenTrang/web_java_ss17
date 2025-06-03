package ra.ss17.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.ss17.entity.ProductCart;
import ra.ss17.repository.ProductCartRepository;

import java.util.List;

@Service
public class ProductCartServiceImpl implements ProductCartService {

    @Autowired
    private ProductCartRepository productCartRepository;

    @Override
    public ProductCart findByCustomerAndProduct(int customerId, int productId) {
        return productCartRepository.findByCustomerAndProduct(customerId, productId);
    }

    @Override
    public void save(ProductCart productCart) {
        productCartRepository.save(productCart);
    }

    @Override
    public void update(ProductCart productCart) {
        productCartRepository.update(productCart);
    }

    @Override
    public List<ProductCart> findByCustomerId(int customerId) {
        return productCartRepository.findByCustomerId(customerId);
    }

    @Override
    public void delete(ProductCart productCart) {
        productCartRepository.delete(productCart);
    }
}
