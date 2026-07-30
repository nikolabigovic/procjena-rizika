package com.nb.procjena_rizika.service.klijent;

import com.nb.procjena_rizika.model.klijent.KlijentFizickoLice;
import com.nb.procjena_rizika.repository.klijent.KlijentFizickoLiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KlijentFizickoLiceService {

   private final KlijentFizickoLiceRepository repository;

    //create

    public KlijentFizickoLice create(KlijentFizickoLice klijentFizickoLice) {
        return repository.save(klijentFizickoLice);

    }

    //read

    public KlijentFizickoLice findById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    public List<KlijentFizickoLice> findByIme(String ime){
        return repository.findByIme(ime);
    }

    public KlijentFizickoLice findByMaticniBr(String jmbg){
        return repository.findByMaticniBr(jmbg);
    }

    public List<KlijentFizickoLice> findByPrezime(String prezime){
        return repository.findByPrezime(prezime);
    }

    //update

    public KlijentFizickoLice update(Integer id, KlijentFizickoLice klijentFizickoLice) {
        repository.findById ( id).orElseThrow();
        klijentFizickoLice.setId(id);
        return repository.save(klijentFizickoLice);
    }

    //delete

    public void deleteById(Integer id) {
        repository.findById (id).orElseThrow();
        repository.deleteById(id);
    }



}
