package pl.sda.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class MainDelete_Delete {
    public static void main(String[] args) {
        // wywolaj try-with-resources ktory zamknie sesje automatycznie po opuszczeniu try
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Najpierw SELECT * FROM Student WHERE id = 2L
            Student student = session.get(Student.class, 2L);

            // jesli udalo sie znalezc
            if (student != null) {
                // DELETE FROM Student WHER id = 2L
                session.remove(student);
            }

            // zatwierdzamy transakcje
            transaction.commit();

        } catch (Exception ioe) {
            // jesli zlapiemy blad, to wywola sie catch

        }

    }
}
