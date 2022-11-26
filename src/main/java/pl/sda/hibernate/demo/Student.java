package pl.sda.hibernate.demo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// Wzorzec projektory builder:
//    Kiedy uzywac:
//      - kiedy chcemy miec wieksza kontrole nad tworzeniem obiektow - mamy na mysli ilosc i kolejnosc parametrow
//      - kiedy chcemy zredukowac ilosc konstruktorow (konstruktory o roznych kombinacjach parametrow)

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id;

    @Column(nullable = false)
    private String imie;
    private LocalDate dataUrodzenia;
    private String kierunekNauczania;
    private String indeks; // index jest slowem zabronionym przez mySql


}
