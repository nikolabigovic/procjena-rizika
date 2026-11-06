package com.nb.procjena_rizika.mapper;

import com.nb.procjena_rizika.dto.request.KFLRequestDto;
import com.nb.procjena_rizika.dto.response.KFLResponseDto;
import com.nb.procjena_rizika.model.klijent.KlijentFizickoLice;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface KlijentFizickoLiceMapper {

    @Mapping(target = "statusId", source = "status.id")
    @Mapping(target = "pravnoLiceId", source = "pravnoLice.id")
    KFLResponseDto toResponse(KlijentFizickoLice klijentFizickoLice);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "pravnoLice", ignore = true)
    KlijentFizickoLice toEntity(KFLRequestDto kflRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "pravnoLice", ignore = true)
    void updateEntity(@MappingTarget KlijentFizickoLice klijentFizickoLice, KFLRequestDto  kflRequestDto);


}
