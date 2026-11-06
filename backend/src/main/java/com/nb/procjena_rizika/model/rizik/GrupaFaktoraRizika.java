package com.nb.procjena_rizika.model.rizik;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="grupa_faktora_rizika")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GrupaFaktoraRizika {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="naziv")
    private String naziv;

    @Column(name="opis")
    private String opis;
}
