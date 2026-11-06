package com.nb.procjena_rizika.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IndikatorOcjenaResponseDto {

    private Integer id;

    private Integer indikatorRizikaId;
    private String indikatorRizikaNaziv;


    private Double ocjena;
}
