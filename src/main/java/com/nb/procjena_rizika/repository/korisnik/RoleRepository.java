package com.nb.procjena_rizika.repository.korisnik;

import com.nb.procjena_rizika.model.korisnik.Role;
import com.nb.procjena_rizika.model.rizik.Ranjivost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    //Role findById(int id);
    Role findByNaziv(String naziv);
}
