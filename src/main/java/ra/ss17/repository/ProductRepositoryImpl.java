package ra.ss17.repository;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ra.ss17.entity.Product;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final SessionFactory sessionFactory;

    public ProductRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> getAll(int page, int size) {
        Session session = sessionFactory.openSession();

        Query<Product> query = session.createQuery("FROM Product", Product.class);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);

        List<Product> products = query.getResultList();
        session.close();

        return products;
    }

    @Override
    public Product findById(int id) {
        Session session = sessionFactory.openSession();

        Product product = session.get(Product.class, id);
        session.close();

        return product;
    }
    @Override
    public void save(Product product) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(product);

        tx.commit();
        session.close();
    }
}
