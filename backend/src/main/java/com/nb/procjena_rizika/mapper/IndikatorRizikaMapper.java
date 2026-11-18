package com.nb.procjena_rizika.mapper;

import com.nb.procjena_rizika.dto.response.IndikatorRizikaResponseDto;
import com.nb.procjena_rizika.model.rizik.IndikatorRizika;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IndikatorRizikaMapper {

    @Mapping(source="grupaFaktoraRizika.naziv", target = "grupaFaktoraRizikaNaziv")
    @Mapping(source="grupaFaktoraRizika.id", target="grupaFaktoraRizikaId")
    IndikatorRizikaResponseDto toResponse(IndikatorRizika indikatorRizika);
    List<IndikatorRizikaResponseDto> toResponse(List<IndikatorRizika> indikatorRizika);
}
