package ra.ss17.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ra.ss17.entity.Customer;

import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final SessionFactory sessionFactory;

    public CustomerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getAll() {
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM Customer", Customer.class);
        List<Customer> customers = query.getResultList();
        session.close();
        return customers;
    }

    @Override
    public Customer findById(int id) {
        Session session = sessionFactory.openSession();
        Customer customer = session.get(Customer.class, id);
        session.close();
        return customer;
    }

    @Override
    public void save(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        session.close();
    }
    @Override
    public List<Customer> findByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM Customer WHERE username = :username AND password = :password", Customer.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Customer> result = query.getResultList();
        session.close();
        return result;
    }


    @Override
    public void update(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<?> query = session.createQuery("DELETE FROM Customer WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
