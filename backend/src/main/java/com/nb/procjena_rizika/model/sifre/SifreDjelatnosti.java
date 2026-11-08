package com.nb.procjena_rizika.model.sifre;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="sifre_djelatnosti")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SifreDjelatnosti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="sifra")
    private String sifra;

    @Column(name="opis")
    private String opis;

    @Column(name="rizik")
    private Boolean rizik;
}
