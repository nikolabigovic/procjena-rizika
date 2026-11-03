package com.nb.procjena_rizika.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KPLRequestDto {

    private String naziv;
    private String sjediste;
    private String pib;
    private String sifraDjelatnosti;
    private LocalDate datumOsnivanjaFirme;
    private LocalDate datumUPO;
    private Boolean resident;
    private String razlogPoslovnogOdnosa;
    private String telefon;
    private String email;
    private Integer firmaId;
}
