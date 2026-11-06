package com.nb.procjena_rizika.service.rizik;


import com.nb.procjena_rizika.model.rizik.GrupaFaktoraRizika;
import com.nb.procjena_rizika.model.rizik.IndikatorOcjena;
import com.nb.procjena_rizika.model.rizik.IndikatorRizika;
import com.nb.procjena_rizika.model.rizik.ProcjenaRizika;
import com.nb.procjena_rizika.repository.rizik.IndikatorOcjenaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndikatorOcjenaService {

    private final IndikatorOcjenaRepository repository;

    //create

    public IndikatorOcjena create(IndikatorOcjena indikatorOcjena) {
        return repository.save(indikatorOcjena);
    }

    //read

    public List<IndikatorOcjena> findByProcjenaId(Integer procjena) {
        return repository.findByProcjenaRizikaId(procjena);
    }

    public List<IndikatorOcjena> findByIndikatorRizikaId(Integer indikator) {
        return repository.findByIndikatorRizikaId(indikator);
    }

//    public List<IndikatorOcjena> findByGrupaFaktoraRizikaId(Integer grupaId){
//        return repository.findByGrupaFaktoraRizikaId(grupaId);
//    }

    // update

    public IndikatorOcjena update(Integer id,  IndikatorOcjena indikatorOcjena) {
        indikatorOcjena.setId(id);
        return repository.save(indikatorOcjena);
    }

    //delete
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
