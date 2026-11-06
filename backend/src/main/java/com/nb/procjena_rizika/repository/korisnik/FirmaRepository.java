package com.nb.procjena_rizika.repository.korisnik;

import com.nb.procjena_rizika.model.korisnik.Firma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirmaRepository extends JpaRepository<Firma, Integer> {
    Firma findByPib(String pib);

    //Firma findById(int id);

    List<Firma> findByNazivFirme(String naziv);

    List<Firma> findBySjediste(String sjediste);

    List<Firma> findBySifraDjelatnosti(String sifraDjelatnosti);





}
