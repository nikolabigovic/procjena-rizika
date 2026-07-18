package com.nb.procjena_rizika.model.rizik;
import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ranjivost")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ranjivost {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="sifra_djelatnosti")
    private String sifraDjelatnosti;

    @Column(name="ocjena")
    private Double ocjena;
}
