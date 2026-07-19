package com.nb.procjena_rizika.repository.rizik;

import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import com.nb.procjena_rizika.model.korisnik.Firma;
import com.nb.procjena_rizika.model.korisnik.Korisnik;
import com.nb.procjena_rizika.model.rizik.ProcjenaRizika;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcjenaRizikaRepository extends JpaRepository<ProcjenaRizika, Integer> {

    //ProcjenaRizika findById(int id);

    List<ProcjenaRizika> findByKorisnikKreator(Korisnik kreator);

    List<ProcjenaRizika> findByKlijentPravnoLice(KlijentPravnoLice klijentPravnoLice);


}
