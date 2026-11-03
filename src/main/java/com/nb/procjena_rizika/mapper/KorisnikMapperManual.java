package com.nb.procjena_rizika.mapper;

import com.nb.procjena_rizika.dto.request.KorisnikRequestDto;
import com.nb.procjena_rizika.dto.response.KorisnikResponseDto;
import com.nb.procjena_rizika.model.korisnik.Korisnik;
import org.springframework.stereotype.Component;

@Component
public class KorisnikMapperManual {

    public KorisnikResponseDto toResponse (Korisnik korisnik) {
        KorisnikResponseDto korisnikResponseDto = new KorisnikResponseDto();

        korisnikResponseDto.setId(korisnik.getId());
        korisnikResponseDto.setUsername(korisnik.getUsername());
        korisnikResponseDto.setEmail(korisnik.getEmail());
        korisnikResponseDto.setIme(korisnik.getIme());
        korisnikResponseDto.setPrezime(korisnik.getPrezime());
        if (korisnik.getRole()!=null){
            korisnikResponseDto.setRoleNaziv(korisnik.getRole().getNaziv());
        }
        if (korisnik.getFirma()!=null){
            korisnikResponseDto.setFirmaId(korisnik.getFirma().getId());
        }

        return korisnikResponseDto;
    }

    public Korisnik toEntity(KorisnikRequestDto korisnikRequestDto) {
        Korisnik korisnik = new Korisnik();
        korisnik.setUsername(korisnikRequestDto.getUsername());
        korisnik.setEmail(korisnikRequestDto.getEmail());
        korisnik.setIme(korisnikRequestDto.getIme());
        korisnik.setPrezime(korisnikRequestDto.getPrezime());
        korisnik.setPassword(korisnikRequestDto.getPassword());

        return korisnik;
    }
}
