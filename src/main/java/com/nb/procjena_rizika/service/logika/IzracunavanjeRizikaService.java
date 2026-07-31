package com.nb.procjena_rizika.service.logika;


import com.nb.procjena_rizika.model.rizik.IndikatorOcjena;
import com.nb.procjena_rizika.model.rizik.Ranjivost;
import com.nb.procjena_rizika.repository.korisnik.FirmaRepository;
import com.nb.procjena_rizika.repository.rizik.RanjivostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class IzracunavanjeRizikaService {

    private final IzracunavanjePretnjeService izracunavanjePretnjeService;
    private final RanjivostRepository  ranjivostRepository;
    private final FirmaRepository  firmaRepository;


    //fetchovanje ranjivosti po sifri djelatonsti
    public Double getRanjivost(String sifraDjelatnosti){
        Double ranjivost= ranjivostRepository.findBySifraDjelatnosti(sifraDjelatnosti).getOcjena();
        return ranjivost;
    }

    //fetchovanje posljedice iz firme
    public Double getPosljedice(String firmaPib){
        Double posljedice=firmaRepository.findByPib(firmaPib).getPosljedice();
        return posljedice;
    }
    //izracunavanje ukupnog rizika duhh
    public Double izracunavanjeUkupnogRizika(List<IndikatorOcjena> lista, String sifraDjelatnosti, String firmaPib){
        Double pretnja=izracunavanjePretnjeService.pretnja(lista);
        Double ranjivost=getRanjivost(sifraDjelatnosti);
        Double posljedice=getPosljedice(firmaPib);

        Double ukupanRizik=pretnja*ranjivost*posljedice;
        ukupanRizik=Math.round(ukupanRizik*100.0)/100.0;
        return ukupanRizik;

    }
    // klasifikovanje ukupnog rizika
    public String klasifikacijaRizika(Double ukupanRizik){
        String klasifikacijaRizik="";
        if (ukupanRizik<=5){
            klasifikacijaRizik="Niži rizik";
        }
        else if(ukupanRizik<=20&&ukupanRizik>5){
            klasifikacijaRizik="Srednje niži rizik";
        }
        else if(ukupanRizik<=50&&ukupanRizik>20){
            klasifikacijaRizik="Srednji rizik";
        }
        else if(ukupanRizik<=95&&ukupanRizik>50){
            klasifikacijaRizik="Srednje visok rizik";
        }
        else if(ukupanRizik<=125&&ukupanRizik>95){
            klasifikacijaRizik="Visok rizik";
        }
        else klasifikacijaRizik="Nepoznato stanje!";
        return klasifikacijaRizik;
    }
}
