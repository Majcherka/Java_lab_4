package dao;

import org.hibernate.SessionFactory;
import util.HibernateUtil;
import model.Shape;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ShapeDAO {


    private final SessionFactory sessionFactory;

    public ShapeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Shape shape) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(shape);
            transaction.commit();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Shape findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Shape.class, id);
        }
    }
    public List<Shape> getAllShapes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Shape", Shape.class).list();
        }
    }
}
