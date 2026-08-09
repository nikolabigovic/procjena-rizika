package com.nb.procjena_rizika.service.rizik;


import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import com.nb.procjena_rizika.model.korisnik.Korisnik;
import com.nb.procjena_rizika.model.rizik.IndikatorOcjena;
import com.nb.procjena_rizika.model.rizik.ProcjenaRizika;
import com.nb.procjena_rizika.repository.klijent.KlijentPravnoLiceRepository;
import com.nb.procjena_rizika.repository.rizik.IndikatorOcjenaRepository;
import com.nb.procjena_rizika.repository.rizik.ProcjenaRizikaRepository;
import com.nb.procjena_rizika.service.logika.IzracunavanjePretnjeService;
import com.nb.procjena_rizika.service.logika.IzracunavanjeRizikaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcjenaRizikaService {

    private final ProcjenaRizikaRepository repository;
    private final IndikatorOcjenaRepository indikatorOcjenaRepository;
    private final IndikatorOcjenaService indikatorOcjenaService;
    private final IzracunavanjePretnjeService izracunavanjePretnjeService;
    private  final IzracunavanjeRizikaService izracunavanjeRizikaService;
    private final KlijentPravnoLiceRepository klijentPravnoLiceRepository;
    //create

    public ProcjenaRizika create(ProcjenaRizika procjenaRizika) {
        List<IndikatorOcjena> listaOcjena=procjenaRizika.getOcjene();



        KlijentPravnoLice klijent= klijentPravnoLiceRepository.findById(procjenaRizika.getKlijentPravnoLice().getId()).orElseThrow();

        var sifra= klijent.getSifraDjelatnosti();
        var pib = klijent.getPib();



        Double ukupniRizik=izracunavanjeRizikaService.izracunavanjeUkupnogRizika(listaOcjena,sifra,pib);

        procjenaRizika.setOcjene(listaOcjena);

        procjenaRizika.setPosljedica(izracunavanjeRizikaService.getPosljedice(pib));
        procjenaRizika.setRanjivost(izracunavanjeRizikaService.getRanjivost(sifra));
        procjenaRizika.setPrijetnja(izracunavanjePretnjeService.pretnja(listaOcjena));

        procjenaRizika.setUkupniRizik(ukupniRizik);
        procjenaRizika.setNivoRizika(izracunavanjeRizikaService.klasifikacijaRizika(ukupniRizik));

        procjenaRizika.setDatumProcjene(LocalDateTime.now());



        return repository.save(procjenaRizika);
    }

    //read
    public ProcjenaRizika findById(int id) {
        return repository.findById(id).orElseThrow();
    }

    public List<ProcjenaRizika> findByKorisnik(Integer korisnik) {
        return repository.findByKorisnikKreatorId(korisnik);
    }

    public List<ProcjenaRizika> findByKlijent(Integer klijent) {
        return repository.findByKlijentPravnoLiceId(klijent);
    }

    public List<ProcjenaRizika> findByNivoRizikaId(Integer nivoRizikaId) {
        return repository.findByNivoRizikaId(nivoRizikaId);
    }

    //update dostupan samo u prvih 7 dana od pravljenja procjene zbog eventualnih user errora.

    public ProcjenaRizika update(Integer id, ProcjenaRizika procjenaRizika) {
        repository.findById(id).orElseThrow();

        if (LocalDateTime.now().isAfter(procjenaRizika.getDatumProcjene().plusDays(7))) {
            throw new RuntimeException("Ne mozete mjenjati procjenu 7 dana nakon sto je  napravljena!");
        }
        else  {
            procjenaRizika.setId(id);
            return repository.save(procjenaRizika);
        }
    }

    public void delete(Integer id) {
        ProcjenaRizika procjenaRizika = findById(id);
        if (LocalDateTime.now().isAfter(procjenaRizika.getDatumProcjene().plusDays(7))) {
            throw new RuntimeException("Ne mozete izbrisati procjenu 7 dana nakon sto je  napravljena!");
        }
        else{
            repository.delete(procjenaRizika);
        }
    }


}
