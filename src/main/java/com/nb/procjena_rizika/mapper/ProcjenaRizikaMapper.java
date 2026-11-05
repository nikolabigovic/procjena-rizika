package com.nb.procjena_rizika.mapper;

import com.nb.procjena_rizika.dto.response.ProcjenaRizikaResponseDto;
import com.nb.procjena_rizika.model.rizik.ProcjenaRizika;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses =IndikatorOcjenaMapper.class)//koristi ovaj mapper za popunjavanje liste
public interface ProcjenaRizikaMapper {

    @Mapping(target="klijentPravnoLiceId", source = "klijentPravnoLice.id")
    @Mapping(target="klijentPravnoLiceNaziv", source = "klijentPravnoLice.naziv")
    @Mapping(target="korisnikKreatorId", source = "korisnikKreator.id")
    @Mapping(target="korisnikKreatorUsername", source = "korisnikKreator.username")
    @Mapping(target="nivoRizikaId", source = "nivoRizika.id")
    @Mapping(target="nivoRizikaNaziv", source = "nivoRizika.naziv")
    ProcjenaRizikaResponseDto toResponse(ProcjenaRizika procjenaRizika);
}
