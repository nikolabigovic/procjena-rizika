package com.nb.procjena_rizika.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KPLRequestDto {

    @NotBlank
    @Size(max=255)
    private String naziv;
    @NotBlank
    @Size(max=255)
    private String sjediste;
    @NotBlank
    @Size(max=20)
    private String pib;
    @NotBlank
    @Size(max=20)
    private String sifraDjelatnosti;
    @Past
    private LocalDate datumOsnivanjaFirme;

    private LocalDate datumUPO;
    @NotNull
    private Boolean resident;
    @Size(max=300)
    private String razlogPoslovnogOdnosa;

    private String telefon;
    @Email
    @Size(max=100)
    private String email;

}
