package com.nb.procjena_rizika.model.rizik;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tezina_grupe_rizika")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TezinaGrupeRizika {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="sifra_djelatnosti")
    private String sifraDjelatnosti;

    @ManyToOne
    @JoinColumn(name="grupa_faktora_rizika_id")
    private GrupaFaktoraRizika grupaFaktoraRizika;

    @Column(name="tezina")
    private Double tezina;
}
