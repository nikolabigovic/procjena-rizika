package com.nb.procjena_rizika.repository.rizik;

import com.nb.procjena_rizika.model.rizik.Ranjivost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RanjivostRepository extends JpaRepository<Ranjivost, Integer> {

    //Ranjivost findById(int id);

    Ranjivost findBySifraDjelatonosti (String sifraDjelatonosti);
}
