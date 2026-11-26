package com.nb.procjena_rizika.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IzlozenostResponseDto {

    private Double ukupanProsjecniRizik;
    private Integer nivoRizikaId;
    private String nivoRizikaNaziv;


    private Integer brojKlijenataSaProcjenom;
    private Integer brojKlijenataBezProcjene;

    private Integer brojVisokoISrednjeVisokoRizicnih;
    private Integer brojProcjenaKojeIsticu;



}
