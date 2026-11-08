package com.nb.procjena_rizika.service.klijent;

import com.nb.procjena_rizika.dto.request.KFLRequestDto;
import com.nb.procjena_rizika.dto.response.KFLResponseDto;
import com.nb.procjena_rizika.mapper.KlijentFizickoLiceMapper;
import com.nb.procjena_rizika.model.klijent.KlijentFizickoLice;
import com.nb.procjena_rizika.repository.klijent.KlijentFizickoLiceRepository;
import com.nb.procjena_rizika.repository.klijent.KlijentPravnoLiceRepository;
import com.nb.procjena_rizika.repository.klijent.StatusFizickogLicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KlijentFizickoLiceService {

   private final KlijentFizickoLiceRepository repository;
   private final KlijentFizickoLiceMapper mapper;
   private final StatusFizickogLicaRepository statusFizickogLicaRepository;
   private final KlijentPravnoLiceRepository klijentPravnoLiceRepository;
    //create

    public KFLResponseDto create(KFLRequestDto kflRequestDto) {
        KlijentFizickoLice kfl = mapper.toEntity(kflRequestDto);
        kfl.setStatus(statusFizickogLicaRepository.findById(kflRequestDto.getStatusId()).orElseThrow());
        kfl.setPravnoLice(klijentPravnoLiceRepository.findById(kflRequestDto.getPravnoLiceId()).orElseThrow());

        repository.save(kfl);
        return mapper.toResponse(kfl);

    }

    //read

    public KFLResponseDto findById(Integer id) {
        return mapper.toResponse(repository.findById(id).orElseThrow());
    }

    public KFLResponseDto findByMaticniBr(String jmbg){
        return mapper.toResponse(repository.findByMaticniBr(jmbg));
    }

    public List<KFLResponseDto> findByStatusId(Integer id) {
        return repository.findByStatusId(id).stream().map(mapper :: toResponse).toList();
    }

    public List<KFLResponseDto> findByIme(String ime){
        return repository.findByIme(ime).stream().map(mapper::toResponse).toList();
    }



    public List<KFLResponseDto> findByPrezime(String prezime){
        return repository.findByPrezime(prezime).stream().map(mapper::toResponse).toList();
    }

    //update

    public KFLResponseDto update(Integer id, KFLRequestDto kflRequestDto) {
        KlijentFizickoLice postojeci = repository.findById ( id).orElseThrow();
        mapper.updateEntity(postojeci,kflRequestDto);
        if(kflRequestDto.getStatusId()!=null){
            postojeci.setStatus(statusFizickogLicaRepository.findById(kflRequestDto.getStatusId()).orElseThrow());
        }
        if (kflRequestDto.getPravnoLiceId()!=null){
            postojeci.setPravnoLice(klijentPravnoLiceRepository.findById(kflRequestDto.getPravnoLiceId()).orElseThrow());
        }
        repository.save(postojeci);
        return mapper.toResponse(postojeci);
    }

    //delete

    public void deleteById(Integer id) {
        repository.findById (id).orElseThrow();
        repository.deleteById(id);
    }



}
