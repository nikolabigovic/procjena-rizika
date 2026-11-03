package com.nb.procjena_rizika.service.klijent;

import com.nb.procjena_rizika.dto.request.KPLRequestDto;
import com.nb.procjena_rizika.dto.response.KPLResponseDto;
import com.nb.procjena_rizika.mapper.KlijentPravnoLiceMapper;
import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import com.nb.procjena_rizika.repository.klijent.KlijentPravnoLiceRepository;
import com.nb.procjena_rizika.repository.korisnik.FirmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //da ne pisem eksplicitno konstruktor
public class KlijentPravnoLiceService {

    private final KlijentPravnoLiceRepository repository;
    private final KlijentPravnoLiceMapper mapper;
    private final FirmaRepository firmaRepository;
//    public KlijentPravnoLiceService (KlijentPravnoLiceRepository repository) {
//        this.repository = repository;
//    }



    //kreiranje
    public KPLResponseDto create(KPLRequestDto kplRequestDto) {
        KlijentPravnoLice klijentPravnoLice = mapper.toEntity(kplRequestDto);
        klijentPravnoLice.setFirma(firmaRepository.findById(kplRequestDto.getFirmaId()).orElseThrow());
        repository.save(klijentPravnoLice);
        return mapper.toResponse(klijentPravnoLice);
    }

    // read

    public KPLResponseDto findById(int id) {
        return mapper.toResponse(repository.findById (id).orElseThrow());
    }

    public KPLResponseDto findByPib(String pib) {
        return mapper.toResponse(repository.findByPib(pib));
    }

    public List<KPLResponseDto> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public List<KPLResponseDto> findByNaziv(String naziv) {
        return repository.findByNaziv(naziv).stream().map(mapper::toResponse).toList();
    }

    public List<KPLResponseDto> findBySjediste(String sjediste) {
        return repository.findBySjediste(sjediste).stream().map(mapper::toResponse).toList();
    }

    public List<KPLResponseDto> findBySifraDjelatnosti(String sifraDjelatnosti) {
        return repository.findBySifraDjelatnosti(sifraDjelatnosti).stream().map(mapper::toResponse).toList();
    }


    //update

    public KPLResponseDto update(Integer Id, KPLRequestDto kplRequestDto) {
        KlijentPravnoLice postojeci= repository.findById ( Id).orElseThrow();
        mapper.updateEntity(postojeci, kplRequestDto);
        repository.save(postojeci);
        return mapper.toResponse(postojeci);
    }



    //deletee
    public void deleteById(int id) {
        repository.findById(id).orElseThrow();
        repository.deleteById(id);
    }
}
