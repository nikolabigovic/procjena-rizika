package com.nb.procjena_rizika.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KorisnikRequestDto {

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
    @NotNull
    private Integer roleId;
    @NotNull
    private Integer firmaId;


}
