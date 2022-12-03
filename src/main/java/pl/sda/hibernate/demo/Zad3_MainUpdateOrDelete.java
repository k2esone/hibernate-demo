package pl.sda.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Zad3_MainUpdateOrDelete {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            System.out.println("Co chcesz zrobić (aktualizuj/usuń)?");
            String odpowiedz = scanner.nextLine();

            if(odpowiedz.equals("aktualizuj")){
                System.out.println("Podaj id studenta którego chcesz aktualizować:");
                String id = scanner.nextLine();
                Long studentId = Long.parseLong(id);

                // Najpierw SELECT * FROM Student WHERE id = 2L
                Student student = session.get(Student.class, studentId);
                if (student == null){
                    System.err.println("Student nie istnieje.");
                } else {
                    // wiemy że istnieje
                    System.out.println("Podaj imie:");
                    String imie = scanner.nextLine();

                    System.out.println("Podaj kierunek:");
                    String kierunek = scanner.nextLine();

                    String indeks = null;
                    do {
                        System.out.println("Podaj numer indeksu (6 znaków):");
                        indeks = scanner.nextLine();
                    }while (indeks.length() != 6);

                    // STD Format to: yyyy-MM-dd
                    LocalDate dataUrodzenia = null;
                    do {
                        System.out.println("Podaj datę urodzenia:");
                        String dataUr = scanner.nextLine();
                        try{
                            dataUrodzenia = LocalDate.parse(dataUr);
                        }catch (DateTimeParseException dtpe){ /*Powtórz jeśli jest exception*/}
                    }while (dataUrodzenia == null);

                    Student studentAktualizowany = Student.builder()
                            .dataUrodzenia(dataUrodzenia)
                            .kierunekNauczania(kierunek)
                            .indeks(indeks)
                            .imie(imie)
                            .id(studentId)
                            .build();

                    session.merge(studentAktualizowany);

                }
            } else if(odpowiedz.equals("usun")) {
                System.out.println("Podaj id studenta którego chcesz usunać:");
                String id = scanner.nextLine();
                Long studentId = Long.parseLong(id);

                // Najpierw SELECT * FROM Student WHERE id = 2L
                Student student = session.get(Student.class, studentId);
                if (student == null){
                    System.err.println("Student nie istnieje.");
                } else {
                    session.remove(student);
                }

            }else {
                System.err.println("Niepoprawna komenda!");
            }

            transaction.commit();
        } catch (Exception ioe) {
            // jeśli złapiemy błąd, to wywoła się catch
            System.err.println("Błąd bazy: " + ioe);
        }
    }
}
