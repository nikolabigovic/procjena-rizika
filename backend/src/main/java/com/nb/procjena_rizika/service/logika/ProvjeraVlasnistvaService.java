package com.nb.procjena_rizika.service.logika;

import com.nb.procjena_rizika.service.klijent.TrenutniKorisnikService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProvjeraVlasnistvaService {//ovaj servis je na kraju totalno redudantan ali cu ga ostaviti

    private final TrenutniKorisnikService trenutniKorisnikService;

    public Boolean nadjiSvog(Integer id) {
        if(trenutniKorisnikService.getFirma().getId().equals(id)) {
            return true;
        }
        else return false;
    }

//    public Boolean nadjiSvog(KlijentFizickoLice klijentFizickoLice) {
//        if(trenutniKorisnikService.getFirma().getId().equals(klijentFizickoLice.getPravnoLice().getFirma().getId())) {
//            return true;
//        }
//        else throw new NoSuchElementException("Ovaj klijent ne pripada vasoj firmi!!");
//    }
//
//    public Boolean nadjiSvog(ProcjenaRizika procjenaRizika) {
//        if(trenutniKorisnikService.getKorisnik().getId().equals(procjenaRizika.getKorisnikKreator().getId())) {
//            return true;
//        }
//        else throw new NoSuchElementException("Ova procjena ne pripada vasoj firmi!!");
//    }
}
