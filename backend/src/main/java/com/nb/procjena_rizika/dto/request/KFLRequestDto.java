package com.nb.procjena_rizika.dto.request;


import jakarta.validation.Constraint;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KFLRequestDto {

    @NotBlank
    @Size(max=100)
    private String ime;

    @NotBlank
    @Size(max=100)
    private String prezime;

    @NotBlank
    @Pattern(regexp="\\d{13}",message = "maticni broj mora biti 13 cifata")//regular expression da constraintuje da bude 13 karaktera i da su sve cifre!
    private String maticniBr;

    @Past(message = "datum mora biti u proslosti!")
    @NotNull
    private LocalDate datumRodjenja;

    @Size(max=255)
    private String mjestoPrebivalista;

    @NotNull
    private Boolean isPel;

    @NotNull
    private Integer pravnoLiceId;

    @NotNull
    private Integer statusId;


}
