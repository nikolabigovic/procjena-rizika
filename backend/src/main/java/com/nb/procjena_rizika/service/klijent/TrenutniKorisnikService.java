package com.nb.procjena_rizika.service.klijent;

import com.nb.procjena_rizika.model.korisnik.Firma;
import com.nb.procjena_rizika.model.korisnik.Korisnik;
import com.nb.procjena_rizika.repository.korisnik.KorisnikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service@RequiredArgsConstructor
public class TrenutniKorisnikService {
    private final KorisnikRepository korisnikRepository;

    public Korisnik getKorisnik(){
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return korisnikRepository.findByUsername(username);
    }

    public Firma getFirma(){
        var korsinik = getKorisnik();
        return korsinik.getFirma();
    }
}
