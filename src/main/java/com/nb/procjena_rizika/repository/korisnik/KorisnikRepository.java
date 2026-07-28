package com.nb.procjena_rizika.repository.korisnik;

import com.nb.procjena_rizika.model.korisnik.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface KorisnikRepository extends JpaRepository<Korisnik,Integer> {

    Korisnik findByIme(String ime);

    Korisnik findByEmail(String email);

    Korisnik findByUsername(String username);

}
