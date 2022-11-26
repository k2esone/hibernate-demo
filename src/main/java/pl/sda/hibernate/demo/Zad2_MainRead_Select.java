package pl.sda.hibernate.demo;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class Zad2_MainRead_Select {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {

            System.out.println("Czy chcesz pobrać listę, czy jednego użytkownika? (lista/szukaj)");
            String odpowiedz = scanner.nextLine();

            if(odpowiedz.equals("lista")){
                TypedQuery<Student> zapytanie = session.createQuery("FROM Student", Student.class);
                List<Student> listaWszystkichStudentow = zapytanie.getResultList();
                for (Student student : listaWszystkichStudentow) {
                    System.out.println(student);
                }
            } else if(odpowiedz.equals("szukaj")) {
                System.out.println("Podaj identyfikator studenta: ");
                String odpowiedzId = scanner.nextLine();
                Long studentId = Long.parseLong(odpowiedzId);

                Student student = session.get(Student.class, studentId);
                if (student == null){
                    System.err.println("Nie znaleziono studenta");
                }else{
                    System.out.println("Student: " + student);
                }
            }else {
                System.err.println("Niepoprawna komenda!");
            }
        } catch (Exception ioe) {
            // jeśli złapiemy błąd, to wywoła się catch
            System.err.println("Błąd bazy: " + ioe);
        }
    }
}
