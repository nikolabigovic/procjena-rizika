package com.nb.procjena_rizika.model.rizik;


import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import com.nb.procjena_rizika.model.korisnik.Korisnik;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="procjena_rizika")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProcjenaRizika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="klijent_pravno_lice_id")
    private KlijentPravnoLice klijentPravnoLice;

    @ManyToOne
    @JoinColumn(name="korisnik_kreator_id")
    private Korisnik korisnikKreator;

    @Column(name="prijetnja")
    private Double prijetnja;

    @Column(name="ranjivost")
    private Double ranjivost;

    @Column(name="posljedica")
    private Double posljedica;

    @Column(name="ukupni_rizik")
    private Double ukupniRizik;

    @ManyToOne
    @JoinColumn(name="nivo_rizika_id")
    private NivoRizika nivoRizika;

    @Column(name="datum_procjene")
    private LocalDateTime datumProcjene;

    @Column(name="datum_naredne_procjene")
    private LocalDate datumNaredneProcjene;

    @Column(name="automatski_rizik")
    private Boolean automatskiRizik;

    @Column(name="obrazlozenje")
    private String obrazlozenje;

    // ovo mi je za mapiranje na create metodu za procjenu rizika. sluzi kao "virtuelna" kolona u tabeli koja ne postoji
    @OneToMany(mappedBy = "procjenaRizika")
    private List<IndikatorOcjena> ocjene= new ArrayList<>();

}
