package pl.sda.hibernate.demo;

import org.hibernate.Session;

public class MainRead_SelectById {
    public static void main(String[] args) {
        // wywolaj try-with-resources ktory zamknie sesje automatycznie po opuszczeniu try
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Student student = session.get(Student.class, 2L);

        // SELECT * FROM Student WHERE
            System.out.println("Znalezlismy studenta: " + student);
        } catch (Exception ioe) {
            // jesli zlapiemy blad, to wywola sie catc
            System.err.println("Blad bazy: " + ioe);
        }
    }
}
