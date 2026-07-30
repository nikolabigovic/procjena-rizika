package com.nb.procjena_rizika.service.korisnik;


import com.nb.procjena_rizika.model.korisnik.Korisnik;
import com.nb.procjena_rizika.repository.korisnik.KorisnikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KorisnikService {

    private final KorisnikRepository repository;

    //CREATE

    public Korisnik create(Korisnik korisnik) {
        return repository.save(korisnik);
    }

    // read

    public Korisnik findById(Integer id){
        return repository.findById(id).orElseThrow();

    }

    public List<Korisnik> findByIme(String ime){
        return repository.findByIme(ime);
    }

    public Korisnik findByUsername(String username) {
        return  repository.findByUsername(username);
    }

//    public Korisnik findByEmail(String email) {
//        return  repository.findByEmail(email);
//    } NE ZNAM OCU LI KORISTIT OVO NE SJECAM SE NI STO SAM GA NAPRAVIO NA REPO SLOJU???
    public List<Korisnik> findAll(){
        return repository.findAll();
    }

    //UPDATE

    public Korisnik update(Integer id, Korisnik korisnik) {
        repository.findById ( id).orElseThrow();
        korisnik.setId(id);
        return repository.save(korisnik);
    }

    //delete

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
