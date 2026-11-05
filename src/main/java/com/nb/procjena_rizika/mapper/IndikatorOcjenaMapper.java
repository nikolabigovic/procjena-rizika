package com.nb.procjena_rizika.mapper;

import com.nb.procjena_rizika.dto.request.IndikatorOcjenaRequestDto;
import com.nb.procjena_rizika.dto.response.IndikatorOcjenaResponseDto;
import com.nb.procjena_rizika.model.rizik.IndikatorOcjena;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IndikatorOcjenaMapper {

    @Mapping(source = "indikatorRizika.naziv", target = "indikatorRizikaNaziv")
    @Mapping(source="indikatorRizika.id", target="indikatorRizikaId")
    IndikatorOcjenaResponseDto toResponse(IndikatorOcjena indikatorOcjena);

    @Mapping(target = "id", ignore = true) //moglo je i bez toEntity al eto da pratim pattern dizajna do sada
    @Mapping(target = "indikatorRizika", ignore = true)
    @Mapping(target = "procjenaRizika", ignore = true)
    IndikatorOcjena toEntity(IndikatorOcjenaRequestDto indikatorOcjenaRequestDto);
}
