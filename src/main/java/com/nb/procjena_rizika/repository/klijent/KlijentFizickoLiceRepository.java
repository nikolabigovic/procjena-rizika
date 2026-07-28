package com.nb.procjena_rizika.repository.klijent;

import com.nb.procjena_rizika.model.klijent.KlijentFizickoLice;
import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KlijentFizickoLiceRepository extends JpaRepository<KlijentFizickoLice, Integer> {

    List<KlijentFizickoLice> findByIme(String ime);
    List<KlijentFizickoLice> findByPrezime(String prezime);
    KlijentFizickoLice findByMaticniBr(String jmbg);

}
