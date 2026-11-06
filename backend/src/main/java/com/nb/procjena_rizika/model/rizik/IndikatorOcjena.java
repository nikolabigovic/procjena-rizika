package com.nb.procjena_rizika.model.rizik;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="indikator_ocjena")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IndikatorOcjena {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="indikator_rizika_id")
    private IndikatorRizika indikatorRizika;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="procjena_rizika_id")
    private ProcjenaRizika procjenaRizika;

    @Column(name="ocjena")
    private Double ocjena;
}
