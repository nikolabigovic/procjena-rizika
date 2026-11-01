package com.nb.procjena_rizika.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KorisnikRequestDto {

    private String ime;
    private String prezime;
    private String username;
    private String email;
    private String password;
    private Integer roleId;
    private Integer firmaId;


}
