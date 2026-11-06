package com.nb.procjena_rizika.repository.rizik;

import com.nb.procjena_rizika.model.rizik.Ranjivost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RanjivostRepository extends JpaRepository<Ranjivost, Integer> {

    //Ranjivost findById(int id);

    Ranjivost findBySifraDjelatnosti (String sifraDjelatonosti);
}
