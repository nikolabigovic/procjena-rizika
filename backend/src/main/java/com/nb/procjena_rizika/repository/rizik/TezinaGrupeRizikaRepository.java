package com.nb.procjena_rizika.repository.rizik;

import com.nb.procjena_rizika.model.rizik.TezinaGrupeRizika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TezinaGrupeRizikaRepository extends JpaRepository<TezinaGrupeRizika,Integer> {

    List<TezinaGrupeRizika> findBySifraDjelatnosti(String sifraDjelatnosti);
}
