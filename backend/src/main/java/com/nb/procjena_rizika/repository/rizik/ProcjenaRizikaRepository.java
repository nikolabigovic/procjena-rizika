package com.nb.procjena_rizika.repository.rizik;

import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import com.nb.procjena_rizika.model.korisnik.Firma;
import com.nb.procjena_rizika.model.korisnik.Korisnik;
import com.nb.procjena_rizika.model.rizik.ProcjenaRizika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProcjenaRizikaRepository extends JpaRepository<ProcjenaRizika, Integer> {

    //ProcjenaRizika findById(int id);

    List<ProcjenaRizika> findByKorisnikKreatorId(Integer kreatorId);

    List<ProcjenaRizika> findByKlijentPravnoLiceId(Integer klijentPravnoLiceId);

    List<ProcjenaRizika> findByNivoRizikaId(Integer nivoRizikaId);

    List<ProcjenaRizika> findTop20ByOrderByDatumProcjeneDesc();

    Optional<ProcjenaRizika> findTopByKlijentPravnoLiceIdOrderByDatumProcjeneDesc(Integer klijentPravnoLiceId); //kako je ovaj jpa kul svgm

    List<ProcjenaRizika> findTop20ByKlijentPravnoLiceFirmaIdOrderByDatumProcjeneDesc(Integer firmaId);

    Optional<ProcjenaRizika> findByIdAndKlijentPravnoLiceFirmaId(Integer id, Integer kreatorFirmaId);
}
