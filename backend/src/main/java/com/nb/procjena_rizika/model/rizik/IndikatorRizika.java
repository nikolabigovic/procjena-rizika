package com.nb.procjena_rizika.model.rizik;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="indikator_rizika")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IndikatorRizika {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="grupa_faktora_rizika_id")
    private GrupaFaktoraRizika grupaFaktoraRizika;

    @Column(name="naziv")
    private String naziv;

    @Column(name="opis")
    private String opis;

}
