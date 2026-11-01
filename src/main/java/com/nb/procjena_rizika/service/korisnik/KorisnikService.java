package com.nb.procjena_rizika.service.korisnik;


import com.nb.procjena_rizika.dto.request.KorisnikRequestDto;
import com.nb.procjena_rizika.dto.response.KorisnikResponseDto;
import com.nb.procjena_rizika.mapper.KorisnikMapper;
import com.nb.procjena_rizika.model.korisnik.Korisnik;
import com.nb.procjena_rizika.repository.korisnik.FirmaRepository;
import com.nb.procjena_rizika.repository.korisnik.KorisnikRepository;
import com.nb.procjena_rizika.repository.korisnik.RoleRepository;
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

    //CREATE

    public KorisnikResponseDto create(KorisnikRequestDto korisnikRequestDto) {
        Korisnik korisnik = korisnikMapper.toEntity(korisnikRequestDto);
        korisnik.setRole(roleRepository.findById(korisnikRequestDto.getRoleId()).orElseThrow());
        korisnik.setFirma(firmaRepository.findById(korisnikRequestDto.getFirmaId()).orElseThrow());
        korisnik.setPassword(passwordEncoder.encode(korisnik.getPassword()));
        repository.save(korisnik);
        return korisnikMapper.toResponse(korisnik);
    }

    // read

    public KorisnikResponseDto findById(Integer id){
        Korisnik korisnik = repository.findById(id).orElseThrow();
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

        if (dto.getIme() != null) {
            postojeci.setIme(dto.getIme());
        }
        if (dto.getPrezime() != null) {
            postojeci.setPrezime(dto.getPrezime());
        }
        if (dto.getEmail() != null) {
            postojeci.setEmail(dto.getEmail());
        }
        if (dto.getUsername() != null) {
            postojeci.setUsername(dto.getUsername());
        }

        if (dto.getPassword()!=null && !dto.getPassword().isBlank()){
            postojeci.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if (dto.getRoleId()!=null){
            postojeci.setRole(roleRepository.findById(dto.getRoleId()).orElseThrow());
        }

        if (dto.getFirmaId()!=null){
            postojeci.setFirma(firmaRepository.findById(dto.getFirmaId()).orElseThrow());
        }

        repository.save(postojeci);
        return korisnikMapper.toResponse(postojeci);
    }

    //delete

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
