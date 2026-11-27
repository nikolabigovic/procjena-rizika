package com.nb.procjena_rizika.service.korisnik;


import com.nb.procjena_rizika.dto.request.KorisnikRequestDto;
import com.nb.procjena_rizika.dto.response.KorisnikResponseDto;
import com.nb.procjena_rizika.exception.ProvjeraVlasnistvaException;
import com.nb.procjena_rizika.mapper.KorisnikMapper;
import com.nb.procjena_rizika.mapper.KorisnikMapperManual;
import com.nb.procjena_rizika.model.korisnik.Korisnik;
import com.nb.procjena_rizika.repository.korisnik.FirmaRepository;
import com.nb.procjena_rizika.repository.korisnik.KorisnikRepository;
import com.nb.procjena_rizika.repository.korisnik.RoleRepository;
import com.nb.procjena_rizika.service.klijent.TrenutniKorisnikService;
import com.nb.procjena_rizika.service.logika.ProvjeraVlasnistvaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class KorisnikService {

    private final KorisnikRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final KorisnikMapper korisnikMapper;
    private final RoleRepository roleRepository;
    private final FirmaRepository firmaRepository;
    private final TrenutniKorisnikService trenutniKorisnikService;
    private final ProvjeraVlasnistvaService  provjeraVlasnistvaService;

    //CREATE

    public KorisnikResponseDto create(KorisnikRequestDto korisnikRequestDto) {
        Korisnik korisnik = korisnikMapper.toEntity(korisnikRequestDto);

        korisnik.setRole(roleRepository.findById(korisnikRequestDto.getRoleId()).orElseThrow());
        korisnik.setFirma(trenutniKorisnikService.getFirma());
        korisnik.setPassword(passwordEncoder.encode(korisnik.getPassword()));
        repository.save(korisnik);
        return korisnikMapper.toResponse(korisnik);
    }

    // read

    public KorisnikResponseDto findById(Integer id) {
        return findByIdAndFirmaId(id, trenutniKorisnikService.getFirma().getId());
    }

    public KorisnikResponseDto findByIdAndFirmaId(Integer id, Integer firmaId) {
        Korisnik korisnik = repository.findByIdAndFirmaId(id, firmaId).orElseThrow();
        return korisnikMapper.toResponse(korisnik);

    }

    public List<KorisnikResponseDto> findByIme(String ime){
        return repository.findByIme(ime).stream().map(korisnikMapper::toResponse).toList();
    }

    public KorisnikResponseDto findByUsername(String username) {
        Korisnik korisnik = repository.findByUsername(username);
        return korisnikMapper.toResponse(korisnik);
    }

    public List<KorisnikResponseDto> findAll(){
        return repository.findAll().stream().map(korisnikMapper::toResponse).toList();
    }

    //UPDATE

    public KorisnikResponseDto update(Integer id, KorisnikRequestDto dto) {

        Korisnik postojeci = repository.findById ( id).orElseThrow();
        if(provjeraVlasnistvaService.nadjiSvog(postojeci.getFirma().getId())){

            korisnikMapper.updateEntity(postojeci, dto);   //da se ne mora rucno kao gore

            if (dto.getPassword()!=null && !dto.getPassword().isBlank()){
                postojeci.setPassword(passwordEncoder.encode(dto.getPassword()));
            }

            if (dto.getRoleId()!=null){
                postojeci.setRole(roleRepository.findById(dto.getRoleId()).orElseThrow());
            }


            postojeci.setFirma(trenutniKorisnikService.getFirma());


            repository.save(postojeci);
            return korisnikMapper.toResponse(postojeci);

        }
        else throw new ProvjeraVlasnistvaException("Nemate pravo mjenjati korisnika koji ne pripada vasoj firmi!");

    }

    //delete

    public void deleteById(Integer id) {
        Korisnik korisnik = repository.findById (id).orElseThrow();
        if(provjeraVlasnistvaService.nadjiSvog(korisnik.getFirma().getId())){
            repository.deleteById(id);
        }
        else throw new ProvjeraVlasnistvaException("Nmate pravo brisati korisnika koji ne pripada vasoj firmi!");

    }
}
