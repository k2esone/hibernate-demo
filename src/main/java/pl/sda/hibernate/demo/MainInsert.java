package pl.sda.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class MainInsert {
    public static void main(String[] args) {
        // wywolaj try-with-resources ktory zamknie sesje automatycznie po opuszczeniu try
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

//            // tworzymy studenta
//            Student student = new Student();
//            student.setImie("Pawel");
//            student.setDataUrodzenia(LocalDate.of(1990, 1, 3));
//            student.setKierunekNauczania("Informatyka");
//            student.setIndeks("123123");

            Student student = Student.builder()
                    .dataUrodzenia(LocalDate.of(1990, 1, 3))
                    .kierunekNauczania("Informatyka")
                    .indeks("123123")
                    .imie("Pawel")
                    .build();

            // SQL: INSERT INTO student values ()...
            // zapisujemy studenta
            session.persist(student);

            // zatwierdzamy transakcje
            transaction.commit();

        } catch (Exception ioe) {
            // jesli zlapiemy blad, to wywola sie catch

        }

    }
}
