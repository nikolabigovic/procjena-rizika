package com.nb.procjena_rizika.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KlijentPravnoLiceDto {  // nastavicu kasnije sa dto-ovima!

    private String naziv;
    private String sjediste;
    private String pib;

    private String sifraDjelatnosti;
    private String email;
    private String telefon;
    private LocalDate datumOsnivanja;
    private LocalDate datumUPO;
    private boolean resident;
}
