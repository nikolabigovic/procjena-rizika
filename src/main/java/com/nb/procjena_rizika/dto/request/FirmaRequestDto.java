package com.nb.procjena_rizika.dto.request;


import lombok.*;


import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FirmaRequestDto {
    private String nazivFirme;
    private String sjediste;
    private String pib;
    private String sifraDjelatnosti;
    private LocalDate datumOsnivanja;
    private String telefon;
    private String email;
    private Double posljedice;
}
