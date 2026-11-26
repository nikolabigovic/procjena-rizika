package com.nb.procjena_rizika.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KorisnikRegistracijaRequestDto { //isto kao korisnikdto samo nema role_id i firma_id

    @NotBlank
    @Size(max=100)
    private String ime;
    @NotBlank
    @Size(max=100)
    private String prezime;
    @NotBlank
    @Size(max=100)
    private String username;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(max=255,min = 8)
    private String password;
}
