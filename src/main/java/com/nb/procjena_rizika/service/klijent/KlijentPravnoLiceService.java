package com.nb.procjena_rizika.service.klijent;

import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import com.nb.procjena_rizika.repository.klijent.KlijentPravnoLiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //da ne pisem eksplicitno konstruktor
public class KlijentPravnoLiceService {

    private final KlijentPravnoLiceRepository repository;

//    public KlijentPravnoLiceService (KlijentPravnoLiceRepository repository) {
//        this.repository = repository;
//    }



    //kreiranje
    public KlijentPravnoLice create(KlijentPravnoLice klijentPravnoLice) {
        return repository.save(klijentPravnoLice);
    }

    // read
    public KlijentPravnoLice findById(int id) {
        return repository.findById(id).orElseThrow();
    }

    public KlijentPravnoLice findByNaziv(String naziv) {
        return repository.findByNaziv(naziv);
    }
    public KlijentPravnoLice findByPib(String pib) {
        return repository.findByPib(pib);
    }
    public List<KlijentPravnoLice> findAll() {
        return repository.findAll();
    }

    //update

    public KlijentPravnoLice update(Integer Id, KlijentPravnoLice klijentPravnoLice) {
        repository.findById ( Id).orElseThrow();
        klijentPravnoLice.setId(Id);
        return repository.save(klijentPravnoLice);
    }
    //deletee
    public void deleteById(int id) {
        repository.findById(id).orElseThrow();
        repository.deleteById(id);
    }
}
