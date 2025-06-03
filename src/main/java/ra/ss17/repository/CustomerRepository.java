package ra.ss17.repository;

import ra.ss17.entity.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> getAll();
    Customer findById(int id);
    void save(Customer customer);
    void update(Customer customer);
    void delete(int id);
    List<Customer> findByUsernameAndPassword(String username, String password);

}
