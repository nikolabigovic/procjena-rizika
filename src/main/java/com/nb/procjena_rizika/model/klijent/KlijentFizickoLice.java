package com.nb.procjena_rizika.model.klijent;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="klijent_fizicko_lice")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KlijentFizickoLice {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="ime")
    private String ime;

    @Column(name="prezime")
    private String prezime;

    @Column(name="maticni_br")
    private String maticniBr;

    @Column(name="datum_rodjenja")
    private LocalDate datumRodjenja;

    @Column(name="mjesto_prebivalista")
    private String mjestoPrebivalista;

    @Column(name="is_pel")
    private Boolean isPel;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="pravno_lice_id")
    private KlijentPravnoLice pravnoLice;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="status_id")
    private StatusFizickogLica status;


}
