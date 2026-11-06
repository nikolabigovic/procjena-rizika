package com.nb.procjena_rizika.dto.response;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FirmaResponseDto {
    private Integer id;
    private String nazivFirme;
    private String sjediste;
    private String pib;
    private String sifraDjelatnosti;
    private LocalDate datumOsnivanja;
    private String telefon;
    private String email;
    private Double posljedice;
}
