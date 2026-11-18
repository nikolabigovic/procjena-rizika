package com.nb.procjena_rizika.mapper;

import com.nb.procjena_rizika.dto.request.KPLRequestDto;
import com.nb.procjena_rizika.dto.response.KPLResponseDto;
import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface KlijentPravnoLiceMapper {

    @Mapping(target="firmaId", source = "firma.id")
    @Mapping(target = "nivoRizikaId", ignore = true)
    @Mapping(target = "nivoRizikaNaziv", ignore = true)
    @Mapping(target = "datumNaredneProcjene", ignore = true)
    KPLResponseDto toResponse(KlijentPravnoLice klijentPravnoLice);

    @Mapping(target = "id", ignore = true)
    KlijentPravnoLice toEntity(KPLRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget KlijentPravnoLice klijentPravnoLice, KPLRequestDto KPLRequestDto);
}
