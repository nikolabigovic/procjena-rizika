package com.nb.procjena_rizika.repository.rizik;

import com.nb.procjena_rizika.model.rizik.IndikatorRizika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IndikatorRizikaRepository extends JpaRepository<IndikatorRizika, Integer> {

    IndikatorRizika findByNaziv(String naziv);

}
