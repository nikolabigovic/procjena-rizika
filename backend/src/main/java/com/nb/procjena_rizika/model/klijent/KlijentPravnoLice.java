package com.nb.procjena_rizika.model.klijent;


import com.nb.procjena_rizika.model.korisnik.Firma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="klijent_pravno_lice")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KlijentPravnoLice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="naziv")
    private String naziv;

    @Column(name="sjediste")
    private String sjediste;

    @Column(name="pib")
    private String pib;

    @Column(name="sifra_djelatnosti")
    private String sifraDjelatnosti;

    @Column(name="datum_osnivanja_firme")
    private LocalDate datumOsnivanjaFirme;

    @Column(name="datum_uspostavljanja_poslovnog_odnosa")
    private LocalDate datumUPO;

    @Column(name="razlog_poslovnog_odnosa")
    private String razlogPoslovnogOdnosa;

    @Column(name="resident")
    private Boolean resident;

    @Column(name="telefon")
    private String telefon;

    @Column(name="email")
    private String email;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="firma_id")
    private Firma firma;
}
