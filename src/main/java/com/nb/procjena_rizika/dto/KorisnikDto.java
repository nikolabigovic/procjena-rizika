package com.nb.procjena_rizika.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KorisnikDto {

    private String ime;
    private String prezime;
    private String username;
    private String email;
    private FirmaDto firmaDto;


}
