package pl.sda.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class MainUpdate_Update {
    public static void main(String[] args) {
        // wywolaj try-with-resources ktory zamknie sesje automatycznie po opuszczeniu try
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // CREATE
            // READ
            // UPDATE
            // DELETE

//            // tworzymy studenta
//            Student student = new Student();
//            student.setImie("Pawel");
//            student.setDataUrodzenia(LocalDate.of(1990, 1, 3));
//            student.setKierunekNauczania("Informatyka");
//            student.setIndeks("123123");

            Student student = Student.builder()
                    .dataUrodzenia(LocalDate.of(1995, 6, 15))
                    .kierunekNauczania("Filozofia")
                    .indeks("321321")
                    .imie("Gawel")
                    .build();

            // SQL: UPDATE student SET ...WHERE id = x
            // Merge:
            // - sluzy do aktualizacji
            // - wymaga podania id, co jest kryterium aktualizacji (aktualizujemy obiekt o podanym id)
            // Persist:
            // - sluzy do wstawiania NOWYCH rekordow do bazy
            // - przewaznie nie podaje sie ID poniewaz jest generowane
            session.merge(student);

            // zatwierdzamy transakcje
            transaction.commit();

        } catch (Exception ioe) {
            // jesli zlapiemy blad, to wywola sie catch

        }

    }
}
