package com.nb.procjena_rizika.mapper;

import com.nb.procjena_rizika.dto.request.KorisnikRequestDto;
import com.nb.procjena_rizika.dto.response.KorisnikResponseDto;
import com.nb.procjena_rizika.model.korisnik.Korisnik;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface KorisnikMapper {

    @Mapping(target="id", ignore = true)
    Korisnik toEntity(KorisnikRequestDto korisnikRequestDto);

    @Mapping(source = "role.naziv", target="roleNaziv" )
    @Mapping(source="firma.id", target="firmaId")
    KorisnikResponseDto toResponse(Korisnik korisnik);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target="password", ignore = true)
    void updateEntity(@MappingTarget Korisnik korisnik, KorisnikRequestDto korisnikRequestDto);

}
