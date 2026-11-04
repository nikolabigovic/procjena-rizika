package com.nb.procjena_rizika.dto.request;


import jakarta.validation.constraints.*;
import lombok.*;


import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FirmaRequestDto {

    @NotBlank//ime frime je obavezno
    @Size(max=200)
    private String nazivFirme;

    @NotBlank//sjediste je obavezno
    private String sjediste;

    @NotBlank(message = "Pib je obavezan!")
    private String pib;

    @NotBlank
    private String sifraDjelatnosti;

    @Past(message = "datum mora biti u proslosti!")
    private LocalDate datumOsnivanja;

    private String telefon;

    @Email
    private String email;

    private Double posljedice;
}
