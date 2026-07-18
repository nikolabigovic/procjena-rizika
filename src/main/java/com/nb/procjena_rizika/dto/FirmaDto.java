package com.nb.procjena_rizika.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FirmaDto {

    private String naziv;
    private String sjediste;
    private String pib;
    private String sifraDjelatnosti;
    private LocalDate datumOsnivanja;
    private String telefon;
    private String email;


}
