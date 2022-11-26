package pl.sda.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Zad1_MainCreate_Insert {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // wywolaj try-with-resources ktory zamknie sesje automatycznie po opuszczeniu try
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            System.out.println("Podaj imie");
            String imie = scanner.nextLine();

            System.out.println("Podaj kierunek");
            String kierunek = scanner.nextLine();

            String indeks = null;
            do {
                System.out.println("Podaj numer indeksu(6 znakow):");
                indeks = scanner.nextLine();
            } while (indeks.length() != 6);

            LocalDate dataUrodzenia = null;
            do {
                System.out.println("Podaj date urodzenia");
                String dataUr = scanner.nextLine();
                try {
                    dataUrodzenia = LocalDate.parse(dataUr);
                } catch (DateTimeParseException dtpe) {// powtorz jesli jest exception }
                }
            } while (dataUrodzenia == null);

            Student student = Student.builder()
                    .dataUrodzenia(dataUrodzenia)
                    .kierunekNauczania(kierunek)
                    .indeks(indeks)
                    .imie(imie)
                    .build();


            transaction.commit();

        } catch (Exception ioe) {
            // jesli zlapiemy blad, to wywola sie catch

        }

    }
}
