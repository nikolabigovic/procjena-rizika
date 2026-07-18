package com.nb.procjena_rizika.repository.klijent;

import com.nb.procjena_rizika.model.klijent.KlijentFizickoLice;
import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface KlijentFizickoLiceRepository extends JpaRepository<KlijentFizickoLice, Integer> {

    KlijentFizickoLice findByIme(String ime);
    KlijentPravnoLice findByPrezime(String prezime);
    KlijentPravnoLice findByMaticniBr(String jmbg);

}
