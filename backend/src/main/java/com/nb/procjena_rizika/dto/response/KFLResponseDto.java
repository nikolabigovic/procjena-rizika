package com.nb.procjena_rizika.dto.response;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KFLResponseDto {

    private Integer id;
    private String ime;
    private String prezime;
    private String maticniBr;
    private LocalDate datumRodjenja;
    private String mjestoPrebivalista;
    private Boolean isPel;
    private Integer pravnoLiceId;
    private Integer statusId;
}
