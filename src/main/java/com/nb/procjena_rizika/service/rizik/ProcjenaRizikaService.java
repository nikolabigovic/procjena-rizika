package com.nb.procjena_rizika.service.rizik;


import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import com.nb.procjena_rizika.model.korisnik.Korisnik;
import com.nb.procjena_rizika.model.rizik.ProcjenaRizika;
import com.nb.procjena_rizika.repository.rizik.ProcjenaRizikaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcjenaRizikaService {

    private final ProcjenaRizikaRepository repository;

    //create

    public ProcjenaRizika create(ProcjenaRizika procjenaRizika) {
        return repository.save(procjenaRizika);
    }

    //read
    public ProcjenaRizika findById(int id) {
        return repository.findById(id).orElse(null);
    }

    public List<ProcjenaRizika> findByKorisnik(Korisnik korisnik) {
        return repository.findByKorisnikKreator(korisnik);
    }

    public List<ProcjenaRizika> findByKlijent(KlijentPravnoLice klijent) {
        return repository.findByKlijentPravnoLice(klijent);
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
