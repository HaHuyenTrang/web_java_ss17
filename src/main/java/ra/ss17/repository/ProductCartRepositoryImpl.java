package ra.ss17.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ra.ss17.entity.ProductCart;

@Repository
public class ProductCartRepositoryImpl implements ProductCartRepository {

    private final SessionFactory sessionFactory;

    public ProductCartRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ProductCart findByCustomerAndProduct(int customerId, int productId) {
        Session session = sessionFactory.openSession();
        Query<ProductCart> query = session.createQuery("FROM ProductCart WHERE customerId = :cid AND productId = :pid", ProductCart.class);
        query.setParameter("cid", customerId);
        query.setParameter("pid", productId);
        ProductCart result = query.uniqueResult();
        session.close();
        return result;
    }

    @Override
    public void save(ProductCart productCart) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(productCart);
        tx.commit();
        session.close();
    }

    @Override
    public void update(ProductCart productCart) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(productCart);
        tx.commit();
        session.close();
    }
}
