package com.nb.procjena_rizika.repository.rizik;

import com.nb.procjena_rizika.model.rizik.IndikatorOcjena;
import com.nb.procjena_rizika.model.rizik.IndikatorRizika;
import com.nb.procjena_rizika.model.rizik.ProcjenaRizika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface IndikatorOcjenaRepository extends JpaRepository<IndikatorOcjena, Integer> {

    List<IndikatorOcjena> findByProcjenaRizikaId(Integer procjenaRizika);
    //IndikatorOcjena findById(int id);

    List<IndikatorOcjena> findByIndikatorRizikaId(Integer indikator);

//    List<IndikatorOcjena> findByGrupaFaktoraRizikaId(Integer grupaFaktoraRizikaId);

}
