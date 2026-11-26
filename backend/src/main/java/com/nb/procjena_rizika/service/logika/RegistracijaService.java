package com.nb.procjena_rizika.service.logika;

import com.nb.procjena_rizika.dto.request.FirmaRequestDto;
import com.nb.procjena_rizika.dto.request.KorisnikRegistracijaRequestDto;
import com.nb.procjena_rizika.dto.request.KorisnikRequestDto;
import com.nb.procjena_rizika.dto.request.RegistracijaRequestDto;
import com.nb.procjena_rizika.dto.response.FirmaResponseDto;
import com.nb.procjena_rizika.dto.response.KorisnikResponseDto;
import com.nb.procjena_rizika.exception.NedozvoljenaRegistracijaException;
import com.nb.procjena_rizika.exception.NepostojecaSifraDjelatnostiException;
import com.nb.procjena_rizika.mapper.FirmaMapper;
import com.nb.procjena_rizika.mapper.KorisnikMapper;
import com.nb.procjena_rizika.model.korisnik.Firma;
import com.nb.procjena_rizika.model.korisnik.Korisnik;
import com.nb.procjena_rizika.repository.korisnik.FirmaRepository;
import com.nb.procjena_rizika.repository.korisnik.KorisnikRepository;
import com.nb.procjena_rizika.repository.korisnik.RoleRepository;
import com.nb.procjena_rizika.repository.rizik.RanjivostRepository;
import com.nb.procjena_rizika.service.korisnik.FirmaService;
import com.nb.procjena_rizika.service.korisnik.KorisnikService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class RegistracijaService {

    private final FirmaRepository firmaRepository;
    private final KorisnikRepository korisnikRepository;

    private final FirmaMapper firmaMapper;

    private final RoleRepository  roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RanjivostRepository ranjivostRepository;

    //create
    @Transactional
    public void registracija(RegistracijaRequestDto registracijaRequestDto) {
        FirmaRequestDto firma= registracijaRequestDto.getFirma();
        KorisnikRegistracijaRequestDto korisnik = registracijaRequestDto.getKorisnik();

        if(firmaRepository.findByPib(firma.getPib())==null &&
                korisnikRepository.findByUsername(korisnik.getUsername())==null){
            Firma firmaEntity=firmaMapper.toEntity(firma);
            if(ranjivostRepository.findBySifraDjelatnosti(firma.getSifraDjelatnosti())==null) {
                throw new NepostojecaSifraDjelatnostiException("Nepostojeca sifra djelatnosti!");
            }
            firmaRepository.save(firmaEntity);

            Korisnik korisnikEntity= new  Korisnik(); //nesto me nije bila volja preko mappera
            korisnikEntity.setFirma(firmaEntity);
            korisnikEntity.setUsername(korisnik.getUsername());
            korisnikEntity.setPassword(passwordEncoder.encode(korisnik.getPassword()));
            korisnikEntity.setRole(roleRepository.findByNaziv("ADMIN"));
            korisnikEntity.setIme(korisnik.getIme());
            korisnikEntity.setPrezime(korisnik.getPrezime());
            korisnikEntity.setEmail(korisnik.getEmail());

            korisnikRepository.save(korisnikEntity);

        }
        else{
            throw new NedozvoljenaRegistracijaException("Username vec postoji ili je firma vec registrovana!");
        }
    }
}
