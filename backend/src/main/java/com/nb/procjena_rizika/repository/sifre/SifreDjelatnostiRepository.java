package com.nb.procjena_rizika.repository.sifre;

import com.nb.procjena_rizika.model.sifre.SifreDjelatnosti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SifreDjelatnostiRepository  extends JpaRepository<SifreDjelatnosti, Integer> {

    SifreDjelatnosti findBySifra(String sifra);

    List<SifreDjelatnosti> findByRizik(Boolean rizik);
}
