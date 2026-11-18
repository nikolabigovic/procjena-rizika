package com.nb.procjena_rizika.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IndikatorRizikaResponseDto {

    private Integer id;
    private Integer grupaFaktoraRizikaId;
    private String naziv;
    private String opis;

    private String grupaFaktoraRizikaNaziv;
}
