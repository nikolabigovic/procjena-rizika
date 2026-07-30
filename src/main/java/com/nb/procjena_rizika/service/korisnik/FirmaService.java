package com.nb.procjena_rizika.service.korisnik;


import com.nb.procjena_rizika.model.korisnik.Firma;
import com.nb.procjena_rizika.repository.korisnik.FirmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FirmaService {

    private final FirmaRepository repository;

    //create

    public Firma create(Firma firma) {
        return repository.save(firma);
    }

    //read

    public Firma findById(Integer id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Firma> findAll() {
        return repository.findAll();
    }

    public List<Firma> findByNaziv(String naziv) {
        return repository.findByNazivFirme(naziv);
    }

    public Firma findByPib(String pib) {
        return repository.findByPib(pib);
    }

    public List<Firma> findBySjediste(String sjediste ) {
        return repository.findBySjediste(sjediste);
    }

    public List<Firma> findBySifraDjelatnosti(String sifraDjelatnosti) {
        return repository.findBySifraDjelatnosti(sifraDjelatnosti);
    }

    //update

    public Firma update(Integer id, Firma firma) {
        repository.findById ( id).orElseThrow();
        firma.setId(id);
        return repository.save(firma);
    }

    //delete

    public void delete(Integer id) {
        repository.findById ( id).orElseThrow();
        repository.deleteById(id);
    }
}
