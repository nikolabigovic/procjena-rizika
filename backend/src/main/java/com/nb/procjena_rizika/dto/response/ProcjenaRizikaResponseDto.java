package com.nb.procjena_rizika.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProcjenaRizikaResponseDto {

    private Integer id;

    private Integer klijentPravnoLiceId;
    private String klijentPravnoLiceNaziv;

    private Integer korisnikKreatorId;
    private String korisnikKreatorUsername;

    private List<IndikatorOcjenaResponseDto> ocjene;

    private Double prijetnja;
    private Double ranjivost;
    private Double posljedica;

    private Double ukupniRizik;

    private Integer nivoRizikaId;
    private String nivoRizikaNaziv;

    private LocalDateTime datumProcjene;
    private LocalDate datumNaredneProcjene;

    private Boolean automatskiRizik;

    private String obrazlozenje;




}
