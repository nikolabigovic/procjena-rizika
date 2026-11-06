package com.nb.procjena_rizika.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IndikatorOcjenaRequestDto {


    @NotNull
    private Integer indikatorRizikaId;

    @NotNull
    @Min(1)
    @Max(5)
    private Double ocjena;
}
