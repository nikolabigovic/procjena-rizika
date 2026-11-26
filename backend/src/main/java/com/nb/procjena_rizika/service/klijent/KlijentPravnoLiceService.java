package com.nb.procjena_rizika.service.klijent;

import com.nb.procjena_rizika.dto.request.KPLRequestDto;
import com.nb.procjena_rizika.dto.response.KPLResponseDto;
import com.nb.procjena_rizika.exception.KlijentVecPostojiExcpetion;
import com.nb.procjena_rizika.exception.ProvjeraVlasnistvaException;
import com.nb.procjena_rizika.mapper.KlijentPravnoLiceMapper;
import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import com.nb.procjena_rizika.model.korisnik.Firma;
import com.nb.procjena_rizika.model.korisnik.Korisnik;
import com.nb.procjena_rizika.model.rizik.ProcjenaRizika;
import com.nb.procjena_rizika.repository.klijent.KlijentPravnoLiceRepository;
import com.nb.procjena_rizika.repository.korisnik.FirmaRepository;
import com.nb.procjena_rizika.repository.korisnik.KorisnikRepository;
import com.nb.procjena_rizika.repository.rizik.ProcjenaRizikaRepository;
import com.nb.procjena_rizika.service.logika.ProvjeraVlasnistvaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor //da ne pisem eksplicitno konstruktor
public class KlijentPravnoLiceService {

    private final KlijentPravnoLiceRepository repository;
    private final KlijentPravnoLiceMapper mapper;
    private final FirmaRepository firmaRepository;
    private final KorisnikRepository korisnikRepository;
    private final ProcjenaRizikaRepository procjenaRizikaRepository;
    private final TrenutniKorisnikService trenutniKorisnikService;
    private final ProvjeraVlasnistvaService provjeraVlasnistvaService;


//ovjde je provjera vlasnistva na svaki endpoint

    //kreiranje
    public KPLResponseDto create(KPLRequestDto kplRequestDto) {
        KlijentPravnoLice klijentPravnoLice = mapper.toEntity(kplRequestDto);

         // sve da ide preko trenutno ulogovanog korisnika
        klijentPravnoLice.setFirma(trenutniKorisnikService.getFirma());
        if (repository.existsByPibAndFirmaId(klijentPravnoLice.getPib(),klijentPravnoLice.getFirma().getId())){
            throw new KlijentVecPostojiExcpetion("");
        }
        repository.save(klijentPravnoLice);
        return mapper.toResponse(klijentPravnoLice);
    }

    // read

    public KPLResponseDto findById(int id) {
        KlijentPravnoLice klijentPravnoLice = repository.findById(id).orElseThrow();
        if (provjeraVlasnistvaService.nadjiSvog(klijentPravnoLice.getFirma().getId())) {  //provjeravam da li trenutno ulogovan korisnik pripada firmi kojoj ovaj kpl pripada
            return mapper.toResponse(klijentPravnoLice);
        }
        else throw new ProvjeraVlasnistvaException("Ovaj klijent ne priprada vasoj firmi!");

    }



    public KPLResponseDto findByPib(String pib) {
        Firma firma = trenutniKorisnikService.getFirma();
        return findByPibAndFirmaId(pib, firma.getId());
    }

    public KPLResponseDto findByPibAndFirmaId(String pib, Integer firmaId) {
            return mapper.toResponse(repository.findByPibAndFirmaId(pib,firmaId));
    }






    public List<KPLResponseDto> findAll() {
        return repository.findAllByFirmaId(trenutniKorisnikService.getFirma().getId()).stream().map(klijent -> {
            KPLResponseDto kplResponseDto = mapper.toResponse(klijent);
            Optional<ProcjenaRizika> procjena= procjenaRizikaRepository.findTopByKlijentPravnoLiceIdOrderByDatumProcjeneDesc(klijent.getId());
            if (procjena.isPresent()) {
                kplResponseDto.setNivoRizikaId(procjena.get().getNivoRizika().getId());
                kplResponseDto.setNivoRizikaNaziv(procjena.get().getNivoRizika().getNaziv());
                kplResponseDto.setDatumNaredneProcjene(procjena.get().getDatumNaredneProcjene());
            }

            return kplResponseDto;
        }).toList();


    }

    public List<KPLResponseDto> findByNaziv(String naziv) {
        return findByNazivAndFirmaId(naziv, trenutniKorisnikService.getFirma().getId());
    }

    public List<KPLResponseDto> findByNazivAndFirmaId(String naziv, Integer firmaId) {
            return repository.findByNazivAndFirmaId(naziv, firmaId).stream().map(mapper::toResponse).toList();
    }



    public List<KPLResponseDto> findBySjediste(String sjediste) {
        return findBySjedisteAndFirmaId(sjediste, trenutniKorisnikService.getFirma().getId());
    }

    public List<KPLResponseDto> findBySjedisteAndFirmaId(String sjediste, Integer firmaId) {
            return repository.findBySjedisteAndFirmaId(sjediste, firmaId).stream().map(mapper::toResponse).toList();
    }




    public List<KPLResponseDto> findBySifraDjelatnosti(String sifraDjelatnosti){
        return findBySifraDjelatnostiAndFirmaId(sifraDjelatnosti, trenutniKorisnikService.getFirma().getId());
    }

    public List<KPLResponseDto> findBySifraDjelatnostiAndFirmaId(String sifraDjelatnosti,  Integer firmaId) {
        return repository.findBySifraDjelatnostiAndFirmaId(sifraDjelatnosti, firmaId).stream().map(mapper::toResponse).toList();
    }


    //update

    public KPLResponseDto update(Integer id, KPLRequestDto kplRequestDto) {
        KlijentPravnoLice klijentPravnoLice = repository.findById(id).orElseThrow();
        if (provjeraVlasnistvaService.nadjiSvog(klijentPravnoLice.getFirma().getId())) {
//            KlijentPravnoLice postojeci= repository.findById ( id).orElseThrow();
            mapper.updateEntity(klijentPravnoLice, kplRequestDto);
            repository.save(klijentPravnoLice);
            return mapper.toResponse(klijentPravnoLice);
        }
        else throw new ProvjeraVlasnistvaException("Ovaj klijent ne priprada vasoj firmi!");
    }



    //deletee
    public void deleteById(int id) {
        KlijentPravnoLice klijentPravnoLice = repository.findById(id).orElseThrow();
        if (provjeraVlasnistvaService.nadjiSvog(klijentPravnoLice.getFirma().getId())) {
            repository.deleteById(id);
        }
        else throw new ProvjeraVlasnistvaException("Ne mozete izbrisati klijente koji ne pripadaju vasoj firmi!");
    }
}
