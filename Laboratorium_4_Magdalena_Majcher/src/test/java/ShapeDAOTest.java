import dao.ShapeDAO;
import model.Shape;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.HibernateUtil;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeDAOTest {

    private static SessionFactory sessionFactory;
    private static ShapeDAO shapeDAO;

    @BeforeAll
    public static void setUp() {
        sessionFactory = HibernateUtil.getSessionFactory();
        shapeDAO = new ShapeDAO(sessionFactory);
    }

    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void testSaveAndFindById() {
        Shape shape = new Shape();
        shape.setType("Circle");
        shape.setArea(100.0);

        // Zapis obiektu
        shapeDAO.save(shape);

        // Pobranie obiektu z bazy
        Shape retrievedShape = shapeDAO.findById(shape.getId());

        assertNotNull(retrievedShape);
        assertEquals("Circle", retrievedShape.getType());
        assertEquals(100.0, retrievedShape.getArea());
    }
}
