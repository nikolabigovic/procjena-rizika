package com.nb.procjena_rizika.repository.rizik;

import com.nb.procjena_rizika.model.rizik.NivoRizika;
import com.nb.procjena_rizika.model.rizik.ProcjenaRizika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface NivoRizikaRepository extends JpaRepository<NivoRizika, Integer> {

    NivoRizika findByNaziv(String naziv);

    //NivoRizika findById(int id);
}
