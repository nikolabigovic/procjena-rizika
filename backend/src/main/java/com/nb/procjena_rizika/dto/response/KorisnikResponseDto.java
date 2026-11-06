package com.nb.procjena_rizika.dto.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KorisnikResponseDto {

    private Integer id;
    private String ime;
    private String prezime;
    private String username;
    private String email;
    private String roleNaziv;
    private Integer firmaId;




}
