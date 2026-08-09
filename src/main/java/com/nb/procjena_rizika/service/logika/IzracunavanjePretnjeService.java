package com.nb.procjena_rizika.service.logika;


import com.nb.procjena_rizika.model.rizik.IndikatorOcjena;
import com.nb.procjena_rizika.model.rizik.IndikatorRizika;
import com.nb.procjena_rizika.repository.rizik.GrupaFaktoraRizikaRepository;
import com.nb.procjena_rizika.repository.rizik.IndikatorRizikaRepository;
import com.nb.procjena_rizika.service.rizik.IndikatorOcjenaService;
import com.nb.procjena_rizika.service.rizik.ProcjenaRizikaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IzracunavanjePretnjeService {

//    private final ProcjenaRizikaService procjenaRizikaService;
    private final GrupaFaktoraRizikaRepository  grupaFaktoraRizikaRepository;
    private final IndikatorOcjenaService  indikatorOcjenaService;
    private final IndikatorRizikaRepository  indikatorRizikaRepository;



    //grupise sve ocjene jedne procjene po grupama
    public Map<Integer, List<IndikatorOcjena>> grupisanjePoFaktorima(List<IndikatorOcjena> lista) {
        Map<Integer, List<IndikatorOcjena>> map = new HashMap<>();

        for (IndikatorOcjena ocjena : lista) {
            IndikatorRizika indikator=indikatorRizikaRepository.findById(ocjena.getIndikatorRizika().getId()).orElseThrow(); //inicijalizujem ga da  ne bi kada dohvata objekat bila setovana na null grupa faktora rizika
            map.computeIfAbsent(indikator.getGrupaFaktoraRizika().getId(), i -> new ArrayList<>()).add(ocjena);
        }
        return map;

    }



    // uzima mapu iz gornje metode i vadi prosjeke po grupama

    public Map<Integer, Double> ocjenaPoGrupama(Map<Integer, List<IndikatorOcjena>> map) {
        Map<Integer,Double> novaMapa = new HashMap<>();
        for(Map.Entry<Integer, List<IndikatorOcjena>> entry : map.entrySet()){
            Integer key = entry.getKey();
            List<IndikatorOcjena> value = entry.getValue();
            Double sum = 0.0;
            for(IndikatorOcjena ocjena : value) {
                sum = sum + ocjena.getOcjena();
            }
            sum=sum/value.size();//ovo value.size je valjda da da projek ne sumu
            sum=Math.round(sum*100.0)/100.0; //da se zaokruzi na 2 decimale
            novaMapa.put(key, sum);
        }
        return novaMapa;

    }



    // izracunava pretnju ponderisanu

    public Double izracunajPretnju(Map<Integer, Double> mapa){

        //ponderi hardcoded za sad:D

        Map<Integer, Double> ponderi= new HashMap<>();

        ponderi.put(1, 0.25);
        ponderi.put(2, 0.20);
        ponderi.put(3, 0.10);
        ponderi.put(4, 0.30);
        ponderi.put(5, 0.15);


        List<Double> lista = new ArrayList<>();
        Double sum = 0.0;


        for(Map.Entry<Integer, Double> entry : mapa.entrySet()){
            Integer key = entry.getKey();
            Double value = entry.getValue();
            value=value*ponderi.get(key);
            lista.add(value);
        }

        for (Double vrijednost: lista){
            sum=sum+vrijednost;
        }
        sum=Math.round(sum*100.0)/100.0;
        return sum;
    }

    // metoda koja poziva sve ove i vraca pretnju

    public Double pretnja (List<IndikatorOcjena> lista){
        Map<Integer, List<IndikatorOcjena>> mapa=grupisanjePoFaktorima(lista);
        Map<Integer,Double> mapa2 = ocjenaPoGrupama(mapa);
        Double pretnja=izracunajPretnju(mapa2);
        return pretnja;


    }


}
