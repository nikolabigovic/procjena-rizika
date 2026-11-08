package com.nb.procjena_rizika.service.rizik;


import com.nb.procjena_rizika.dto.request.IndikatorOcjenaRequestDto;
import com.nb.procjena_rizika.dto.request.ProcjenaRizikaRequestDto;
import com.nb.procjena_rizika.dto.response.IndikatorOcjenaResponseDto;
import com.nb.procjena_rizika.dto.response.ProcjenaRizikaResponseDto;
import com.nb.procjena_rizika.exception.IzmjenaNijeDozvoljenaException;
import com.nb.procjena_rizika.mapper.IndikatorOcjenaMapper;
import com.nb.procjena_rizika.mapper.ProcjenaRizikaMapper;
import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import com.nb.procjena_rizika.model.korisnik.Korisnik;
import com.nb.procjena_rizika.model.rizik.IndikatorOcjena;
import com.nb.procjena_rizika.model.rizik.IndikatorRizika;
import com.nb.procjena_rizika.model.rizik.NivoRizika;
import com.nb.procjena_rizika.model.rizik.ProcjenaRizika;
import com.nb.procjena_rizika.repository.klijent.KlijentFizickoLiceRepository;
import com.nb.procjena_rizika.repository.klijent.KlijentPravnoLiceRepository;
import com.nb.procjena_rizika.repository.korisnik.FirmaRepository;
import com.nb.procjena_rizika.repository.korisnik.KorisnikRepository;
import com.nb.procjena_rizika.repository.rizik.IndikatorOcjenaRepository;
import com.nb.procjena_rizika.repository.rizik.IndikatorRizikaRepository;
import com.nb.procjena_rizika.repository.rizik.NivoRizikaRepository;
import com.nb.procjena_rizika.repository.rizik.ProcjenaRizikaRepository;
import com.nb.procjena_rizika.repository.sifre.SifreDjelatnostiRepository;
import com.nb.procjena_rizika.service.logika.IzracunavanjePretnjeService;
import com.nb.procjena_rizika.service.logika.IzracunavanjeRizikaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcjenaRizikaService {

    private final ProcjenaRizikaRepository procjenaRizikaRepository;
    private final IzracunavanjePretnjeService izracunavanjePretnjeService;
    private  final IzracunavanjeRizikaService izracunavanjeRizikaService;
    private final KlijentPravnoLiceRepository klijentPravnoLiceRepository;
    private final ProcjenaRizikaMapper procjenaRizikaMapper;
    private final IndikatorOcjenaMapper indikatorOcjenaMapper;
    private final IndikatorRizikaRepository indikatorRizikaRepository;
    private final KorisnikRepository korisnikRepository;
    private final SifreDjelatnostiRepository sifreDjelatnostiRepository;
    private final KlijentFizickoLiceRepository klijentFizickoLiceRepository;
    private final NivoRizikaRepository nivoRizikaRepository;
    //create
    @Transactional//da osiguram da ce cijela biti unesena uvijek
    public ProcjenaRizikaResponseDto create(ProcjenaRizikaRequestDto procjenaRizikaRequestDto, String username) {  //username se iz tokena vadi i prosljedjuje
        Korisnik korisnik = korisnikRepository.findOptionalByUsername(username).orElseThrow();
        KlijentPravnoLice klijent = klijentPravnoLiceRepository.findById(procjenaRizikaRequestDto.getKlijentPravnoLiceId()).orElseThrow();

        ProcjenaRizika procjenaRizika = new ProcjenaRizika();
        procjenaRizika.setKlijentPravnoLice(klijent);
        procjenaRizika.setObrazlozenje(procjenaRizikaRequestDto.getObrazlozenje());
        procjenaRizika.setKorisnikKreator(korisnik);
        procjenaRizika.setDatumProcjene(LocalDateTime.now());
        izracunavanjeOcjena(procjenaRizika, procjenaRizikaRequestDto);



        ProcjenaRizika sacuvana = procjenaRizikaRepository.save(procjenaRizika);
        return procjenaRizikaMapper.toResponse(sacuvana);
    }

    //read
    public ProcjenaRizikaResponseDto findById(int id) {
        return procjenaRizikaMapper.toResponse(procjenaRizikaRepository.findById(id).orElseThrow());
    }

    public List<ProcjenaRizikaResponseDto> findByKorisnik(Integer korisnik) {
        return procjenaRizikaRepository.findByKorisnikKreatorId(korisnik).stream().map(procjenaRizikaMapper::toResponse).toList();
    }

    public List<ProcjenaRizikaResponseDto> findByKlijent(Integer klijent) {
        return procjenaRizikaRepository.findByKlijentPravnoLiceId(klijent).stream().map(procjenaRizikaMapper::toResponse).toList();
    }

    public List<ProcjenaRizikaResponseDto> findByNivoRizikaId(Integer nivoRizikaId) {
        return procjenaRizikaRepository.findByNivoRizikaId(nivoRizikaId).stream().map(procjenaRizikaMapper::toResponse).toList();
    }





    //update dostupan samo u prvih 7 dana od pravljenja procjene zbog eventualnih user errora.

    private void izracunavanjeOcjena(ProcjenaRizika procjenaRizika, ProcjenaRizikaRequestDto procjenaRizikaRequestDto){//identicno kao create metoda samo bez mjenjanja datuma procjene



        for (IndikatorOcjenaRequestDto indikatorOcjenaRequestDto : procjenaRizikaRequestDto.getListaOcjena()) {
            IndikatorOcjena ocjena = indikatorOcjenaMapper.toEntity(indikatorOcjenaRequestDto);
            IndikatorRizika indikatorRizika = indikatorRizikaRepository.findById(indikatorOcjenaRequestDto.getIndikatorRizikaId()).orElseThrow(); //ovo mi je trebalo masu da ukapiram
            ocjena.setIndikatorRizika(indikatorRizika);
            ocjena.setProcjenaRizika(procjenaRizika);
            procjenaRizika.getOcjene().add(ocjena);
        }
            List<IndikatorOcjena> listaOcjena=procjenaRizika.getOcjene();

            KlijentPravnoLice klijent=procjenaRizika.getKlijentPravnoLice();
            var sifra= klijent.getSifraDjelatnosti();
            var pib= procjenaRizika.getKorisnikKreator().getFirma().getPib();

            Double prijetnja=izracunavanjePretnjeService.pretnja(listaOcjena,sifra);
            Double ranjivost=izracunavanjeRizikaService.getRanjivost(sifra);
            Double posljedica=izracunavanjeRizikaService.getPosljedice(pib);


            Double ukupniRizik=izracunavanjeRizikaService.izracunavanjeUkupnogRizika(prijetnja,ranjivost,posljedica); //promjenjena logika izracunavanja ukunogrizika zbog dupliranja racunanja bez potrebe, stara verzija se moze vidjeti u starim commitovima


            procjenaRizika.setPosljedica(posljedica);
            procjenaRizika.setRanjivost(ranjivost);
            procjenaRizika.setPrijetnja(prijetnja);

            procjenaRizika.setUkupniRizik(ukupniRizik);

            NivoRizika nivoRizika=izracunavanjeRizikaService.klasifikacijaRizika(ukupniRizik);
            procjenaRizika.setAutomatskiRizik(false);

            //provjera automatskog rizika, ako je fl pel, ako ne postoji stvarni vlasnik i ako je sifra djelatnosti rizicna ili ako je checkiran automatski rizik rucno
            if(klijentFizickoLiceRepository.existsByPravnoLiceIdAndIsPelTrue(klijent.getId()) ||
                    !klijentFizickoLiceRepository.existsByPravnoLiceIdAndStatusId(klijent.getId(),1) ||
                    izracunavanjeRizikaService.daLiJeSifraRizicna(sifreDjelatnostiRepository.findBySifra(sifra)) ||
                    Boolean.TRUE.equals(procjenaRizikaRequestDto.getAutomatskiRizik())){//ovo boleaan true je tu da bi se null vrijednost shvatala kao false
                nivoRizika=nivoRizikaRepository.findById(5).orElseThrow();
                procjenaRizika.setAutomatskiRizik(true);
            }


            procjenaRizika.setNivoRizika(nivoRizika);

            procjenaRizika.setDatumNaredneProcjene(izracunavanjeRizikaService.izracunavanjeDatumaNaredneProcjene(nivoRizika));



    }



    @Transactional
    public ProcjenaRizikaResponseDto update(Integer id, ProcjenaRizikaRequestDto procjenaRizikaRequestDto, String username) {
        ProcjenaRizika postojeca= procjenaRizikaRepository.findById(id).orElseThrow();

        if (LocalDateTime.now().isAfter(postojeca.getDatumProcjene().plusDays(7))) {
            throw new IzmjenaNijeDozvoljenaException("Ne mozete mjenjati procjenu 7 dana nakon sto je  napravljena!");
        }
        postojeca.setObrazlozenje(procjenaRizikaRequestDto.getObrazlozenje());

        postojeca.getOcjene().clear();

        izracunavanjeOcjena(postojeca, procjenaRizikaRequestDto);

        ProcjenaRizika sacuvana = procjenaRizikaRepository.save(postojeca);

        return procjenaRizikaMapper.toResponse(sacuvana);

    }

    public void delete(Integer id) {
        ProcjenaRizika procjenaRizika = procjenaRizikaRepository.findById(id).orElseThrow();
        if (LocalDateTime.now().isAfter(procjenaRizika.getDatumProcjene().plusDays(7))) {
            throw new IzmjenaNijeDozvoljenaException("Ne mozete izbrisati procjenu 7 dana nakon sto je  napravljena!");
        }

            procjenaRizikaRepository.delete(procjenaRizika);

    }


}
