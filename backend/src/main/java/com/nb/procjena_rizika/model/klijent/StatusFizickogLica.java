package com.nb.procjena_rizika.model.klijent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="status_fizickog_lica")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatusFizickogLica {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="naziv")
    private String naziv;

    @Column(name="opis")
    private String opis;
}
