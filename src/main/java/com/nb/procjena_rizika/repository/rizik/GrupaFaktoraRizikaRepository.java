package com.nb.procjena_rizika.repository.rizik;

import com.nb.procjena_rizika.model.rizik.GrupaFaktoraRizika;
import com.nb.procjena_rizika.model.rizik.ProcjenaRizika;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupaFaktoraRizikaRepository extends JpaRepository<GrupaFaktoraRizika, Integer> {

    //GrupaFaktoraRizika findById(int id);

    GrupaFaktoraRizika findByNaziv(String naziv);
}
