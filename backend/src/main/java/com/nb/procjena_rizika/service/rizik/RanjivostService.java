package com.nb.procjena_rizika.service.rizik;


import com.nb.procjena_rizika.model.rizik.Ranjivost;
import com.nb.procjena_rizika.repository.rizik.RanjivostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RanjivostService {

    private final RanjivostRepository repository;

    public Ranjivost findBySifraDjelatnosti(String sifraDjelatnosti) {
        return repository.findBySifraDjelatnosti(sifraDjelatnosti);
    }
}
