package com.nb.procjena_rizika.repository.korisnik;

import com.nb.procjena_rizika.model.korisnik.Firma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirmaRepository extends JpaRepository<Firma, Integer> {
    Firma findByPib(String pib);

    //Firma findById(int id);

    Firma findByNazivFirme(String naziv);




}
