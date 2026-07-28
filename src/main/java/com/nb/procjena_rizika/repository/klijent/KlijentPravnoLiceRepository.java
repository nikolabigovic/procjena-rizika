package com.nb.procjena_rizika.repository.klijent;

import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import com.nb.procjena_rizika.model.korisnik.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlijentPravnoLiceRepository extends JpaRepository<KlijentPravnoLice,Integer> {

    KlijentPravnoLice findByNaziv(String naziv);
    KlijentPravnoLice findByPib(String pib);
    //KlijentPravnoLice findById(int id);




}
