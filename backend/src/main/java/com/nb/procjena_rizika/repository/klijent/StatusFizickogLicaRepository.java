package com.nb.procjena_rizika.repository.klijent;

import com.nb.procjena_rizika.model.klijent.StatusFizickogLica;
import com.nb.procjena_rizika.model.rizik.Ranjivost;
import com.nb.procjena_rizika.repository.rizik.RanjivostRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusFizickogLicaRepository extends JpaRepository<StatusFizickogLica,Integer>{

    //StatusFizickogLica findById(int id);

    StatusFizickogLica findByNaziv(String naziv);
}
