package pl.sda.hibernate.demo;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.List;

public class MainRead_SelectList {
    public static void main(String[] args) {
        // wywolaj try-with-resources ktory zamknie sesje automatycznie po opuszczeniu try
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            TypedQuery<Student> zapytanie = session.createQuery("from Student", Student.class);
            List<Student> listaWszystkichStudentow = zapytanie.getResultList();

//            System.out.println(listaWszystkichStudentow);
            for (Student student : listaWszystkichStudentow) {
                System.out.println(student);
            }


        } catch (Exception ioe) {
            // jesli zlapiemy blad, to wywola sie catch
            System.err.println("Blad bazy: " + ioe);

        }
    }
}
