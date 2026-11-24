package com.nb.procjena_rizika.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegistracijaRequestDto {

    @NotNull
    @Valid
    private FirmaRequestDto firma;

    @NotNull
    @Valid
    private KorisnikRegistracijaRequestDto korisnik;

}
