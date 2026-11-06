package com.nb.procjena_rizika.service.korisnik;


import com.nb.procjena_rizika.dto.request.FirmaRequestDto;
import com.nb.procjena_rizika.dto.response.FirmaResponseDto;
import com.nb.procjena_rizika.mapper.FirmaMapper;
import com.nb.procjena_rizika.model.korisnik.Firma;
import com.nb.procjena_rizika.repository.korisnik.FirmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FirmaService {

    private final FirmaRepository repository;
    private final FirmaMapper mapper;
    //create

    public FirmaResponseDto create(FirmaRequestDto firmaRequestDto) {
        Firma firma = mapper.toEntity(firmaRequestDto);
        repository.save(firma);
        return mapper.toResponse(firma);
    }

    //read

    public FirmaResponseDto findById(Integer id) {
        Firma firma = repository.findById (id).orElseThrow();
        return mapper.toResponse(firma);

    }

    public FirmaResponseDto findByPib(String pib) {
        Firma firma = repository.findByPib (pib);
        return mapper.toResponse(firma);
    }

    public List<FirmaResponseDto> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public List<FirmaResponseDto> findByNaziv(String naziv) {
        return repository.findByNazivFirme(naziv).stream().map(mapper::toResponse).toList();
    }


    public List<FirmaResponseDto> findBySjediste(String sjediste ) {
        return repository.findBySjediste(sjediste).stream().map(mapper::toResponse).toList();
    }

    public List<FirmaResponseDto> findBySifraDjelatnosti(String sifraDjelatnosti) {
        return repository.findBySifraDjelatnosti(sifraDjelatnosti).stream().map(mapper::toResponse).toList();
    }

    //update

    public FirmaResponseDto update(Integer id, FirmaRequestDto firmaRequestDto) {
        Firma postojeca = repository.findById (id).orElseThrow();

        mapper.updateEntity(postojeca, firmaRequestDto);
        repository.save(postojeca);
        return mapper.toResponse(postojeca);
    }

    //delete

    public void delete(Integer id) {
        repository.findById ( id).orElseThrow();
        repository.deleteById(id);
    }
}
