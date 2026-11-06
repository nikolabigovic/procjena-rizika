package com.nb.procjena_rizika.mapper;

import com.nb.procjena_rizika.dto.request.FirmaRequestDto;
import com.nb.procjena_rizika.dto.response.FirmaResponseDto;
import com.nb.procjena_rizika.model.korisnik.Firma;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FirmaMapper {

    @Mapping(target ="id", ignore = true)
    Firma toEntity(FirmaRequestDto firmaRequestDto);


    FirmaResponseDto toResponse(Firma firma);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Firma firma, FirmaRequestDto firmaRequestDto);

}
