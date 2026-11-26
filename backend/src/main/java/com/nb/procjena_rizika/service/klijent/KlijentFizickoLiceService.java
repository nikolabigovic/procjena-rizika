package com.nb.procjena_rizika.service.klijent;

import com.nb.procjena_rizika.dto.request.KFLRequestDto;
import com.nb.procjena_rizika.dto.response.KFLResponseDto;
import com.nb.procjena_rizika.exception.ProvjeraVlasnistvaException;
import com.nb.procjena_rizika.mapper.KlijentFizickoLiceMapper;
import com.nb.procjena_rizika.model.klijent.KlijentFizickoLice;
import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import com.nb.procjena_rizika.repository.klijent.KlijentFizickoLiceRepository;
import com.nb.procjena_rizika.repository.klijent.KlijentPravnoLiceRepository;
import com.nb.procjena_rizika.repository.klijent.StatusFizickogLicaRepository;
import com.nb.procjena_rizika.service.logika.ProvjeraVlasnistvaService;
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
   private final ProvjeraVlasnistvaService provjeraVlasnistvaService;
   private final TrenutniKorisnikService trenutniKorisnikService;
    //create

    public KFLResponseDto create(KFLRequestDto kflRequestDto) {
        KlijentPravnoLice kpl= klijentPravnoLiceRepository.findById(kflRequestDto.getPravnoLiceId()).orElseThrow();

        if(provjeraVlasnistvaService.nadjiSvog(kpl.getFirma().getId())){
            KlijentFizickoLice kfl = mapper.toEntity(kflRequestDto);
            kfl.setStatus(statusFizickogLicaRepository.findById(kflRequestDto.getStatusId()).orElseThrow());
            kfl.setPravnoLice(klijentPravnoLiceRepository.findById(kflRequestDto.getPravnoLiceId()).orElseThrow());

            repository.save(kfl);
            return mapper.toResponse(kfl);
        }

        else throw new ProvjeraVlasnistvaException("Nemate pravo da napravite fizicko lice za firmu za koju niste ovlasteni!");


    }

    //read            provjeru nad vlasnistvom radim samo za create,update,delete i find by id za sad, ako odlucim ove druge endpointe da koristim dodacu provjeru u buducnosti ili cu ih izbrisati

    public KFLResponseDto findById(Integer id) {
        return findByIdAndPravnoLiceFirmaId(id, trenutniKorisnikService.getFirma().getId());
    }

    public KFLResponseDto findByIdAndPravnoLiceFirmaId(Integer id, Integer firmaId) {
        KlijentPravnoLice kpl= klijentPravnoLiceRepository.findById(id).orElseThrow();
        if(kpl.getFirma().getId().equals(firmaId)){
            return mapper.toResponse(repository.findByIdAndPravnoLiceFirmaId(id,firmaId));
        }
        else throw new ProvjeraVlasnistvaException("Nemate pravo da pristupi fizikom licu!")


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
        if (provjeraVlasnistvaService.nadjiSvog(postojeci.getPravnoLice().getFirma().getId())) {
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
        else throw new ProvjeraVlasnistvaException("Ovo fizicko lice ne pripada vasoj firmi!");

    }

    //delete

    public void deleteById(Integer id) {

        KlijentFizickoLice kfl = repository.findById (id).orElseThrow();
        if(provjeraVlasnistvaService.nadjiSvog(kfl.getPravnoLice().getFirma().getId())) {
            repository.deleteById(id);
        }
        else throw new ProvjeraVlasnistvaException("Ne mozete brisati fizicka lica koja nisu klijenti vase firme!");
    }



}
