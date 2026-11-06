package com.nb.procjena_rizika.model.korisnik;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="firma")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Firma {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="naziv_firme")
    private String nazivFirme;

    @Column(name="sjediste")
    private String sjediste;

    @Column(name="pib")
    private String pib;

    @Column(name="sifra_djelatnosti")
    private String sifraDjelatnosti;

    @Column(name="datum_osnivanja_firme")
    private LocalDate datumOsnivanja;

    @Column(name="telefon")
    private String telefon;

    @Column(name="email")
    private String email;

    @Column(name="posljedice")
    private Double posljedice;

}
