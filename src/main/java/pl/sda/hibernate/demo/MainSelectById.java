package pl.sda.hibernate.demo;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.List;

public class MainSelectById {
    public static void main(String[] args) {
// wywolaj try-with-resources ktory zamknie sesje automatycznie po opuszczeniu try
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            TypedQuery<Student> zapytanie = session.createQuery("from Student", Student.class);
            List<Student> listaWszystkichStudentow = zapytanie.getResultList();
    }
}
