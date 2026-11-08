package com.nb.procjena_rizika.dto.request;

import com.nb.procjena_rizika.model.rizik.IndikatorOcjena;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProcjenaRizikaRequestDto {

    @NotNull
    private Integer klijentPravnoLiceId;

    @Size(min = 1, max = 6000)
    private String obrazlozenje;

    @NotEmpty
    @Valid
    private List<IndikatorOcjenaRequestDto> listaOcjena;

    private Boolean automatskiRizik;


}
