package com.nb.procjena_rizika.service.logika;


import com.nb.procjena_rizika.model.rizik.IndikatorOcjena;
import com.nb.procjena_rizika.model.rizik.NivoRizika;
import com.nb.procjena_rizika.model.rizik.Ranjivost;
import com.nb.procjena_rizika.repository.korisnik.FirmaRepository;
import com.nb.procjena_rizika.repository.rizik.NivoRizikaRepository;
import com.nb.procjena_rizika.repository.rizik.RanjivostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class IzracunavanjeRizikaService {

    private final IzracunavanjePretnjeService izracunavanjePretnjeService;
    private final RanjivostRepository ranjivostRepository;
    private final FirmaRepository firmaRepository;
    private final NivoRizikaRepository nivoRizikaRepository;

    //fetchovanje ranjivosti po sifri djelatonsti
    public Double getRanjivost(String sifraDjelatnosti) {
        Double ranjivost = ranjivostRepository.findBySifraDjelatnosti(sifraDjelatnosti).getOcjena();
        return ranjivost;
    }

    //fetchovanje posljedice iz firme
    public Double getPosljedice(String firmaPib) {
        Double posljedice = firmaRepository.findByPib(firmaPib).getPosljedice();
        return posljedice;
    }

    //izracunavanje ukupnog rizika duhh
    public Double izracunavanjeUkupnogRizika(List<IndikatorOcjena> lista, String sifraDjelatnosti, String firmaPib) {
        Double pretnja = izracunavanjePretnjeService.pretnja(lista);
        Double ranjivost = getRanjivost(sifraDjelatnosti);
        Double posljedice = getPosljedice(firmaPib);

        Double ukupanRizik = pretnja * ranjivost * posljedice;
        ukupanRizik = Math.round(ukupanRizik * 100.0) / 100.0;
        return ukupanRizik;

    }

    // klasifikovanje ukupnog rizika
    public NivoRizika klasifikacijaRizika(Double ukupanRizik) {

        if (ukupanRizik <= 5) {
            return nivoRizikaRepository.findById(1).orElseThrow();

        } else if (ukupanRizik <= 20 && ukupanRizik > 5) {
            return nivoRizikaRepository.findById(2).orElseThrow();
        } else if (ukupanRizik <= 50 && ukupanRizik > 20) {
            return nivoRizikaRepository.findById(3).orElseThrow();
        } else if (ukupanRizik <= 95 && ukupanRizik > 50) {
            return nivoRizikaRepository.findById(4).orElseThrow();
        } else if (ukupanRizik <= 125 && ukupanRizik > 95) {
            return nivoRizikaRepository.findById(5).orElseThrow();
        } else return nivoRizikaRepository.findById(6).orElseThrow();
    }
    // setovanje datuma sledece procjene na osnovu nivoa rizika:

    public LocalDate izracunavanjeDatumaNaredneProcjene(NivoRizika nivoRizika){
        var id=nivoRizika.getId();
        if (id==1 || id==2) return LocalDate.now().plusMonths(12);
        else if (id==3) return LocalDate.now().plusMonths(6);
        else if (id==4) return LocalDate.now().plusMonths(3);
        else if (id==5) return LocalDate.now().plusMonths(1);
        else return LocalDate.now().plusDays(30);
    }
}



