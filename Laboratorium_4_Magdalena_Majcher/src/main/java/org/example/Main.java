package main;

import dao.ShapeDAO;
import model.Shape;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        // Konfiguracja Hibernate
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        // Utworzenie DAO
        ShapeDAO shapeDAO = new ShapeDAO(sessionFactory);

        try {
            // Utworzenie nowego obiektu Shape
            Shape newShape = new Shape();
            newShape.setType("Circle");
            newShape.setArea(78.5);

            // Zapisanie obiektu do bazy
            shapeDAO.save(newShape);
            System.out.println("Shape saved: " + newShape);

            // Pobranie obiektu z bazy
            Shape retrievedShape = shapeDAO.findById(newShape.getId());
            System.out.println("Shape retrieved: " + retrievedShape);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Zamknięcie SessionFactory
            sessionFactory.close();
        }
    }
}
