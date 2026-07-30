package com.nb.procjena_rizika.controller.korisnik;


import com.nb.procjena_rizika.model.korisnik.Firma;
import com.nb.procjena_rizika.repository.korisnik.FirmaRepository;
import com.nb.procjena_rizika.service.korisnik.FirmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/firma")
@RequiredArgsConstructor
public class FirmaController {

    private final FirmaService firmaService;

    @PostMapping
    public ResponseEntity<Firma> create(@RequestBody Firma firma){
        return new ResponseEntity<>(firmaService.create(firma), HttpStatus.CREATED);
    }




    @GetMapping("/{id}")
    public ResponseEntity<Firma> findById(@PathVariable Integer id){
        return ResponseEntity.ok(firmaService.findById(id));
    }

    @GetMapping("/pib/{pib}")
    public ResponseEntity<Firma> findByPib(@PathVariable String pib){
        return ResponseEntity.ok(firmaService.findByPib(pib));
    }

    @GetMapping("/naziv/{naziv}")
    public ResponseEntity<List<Firma>> findByNaziv(@PathVariable String naziv){
        return ResponseEntity.ok(firmaService.findByNaziv(naziv));
    }
    //treba i ovdje dodatri sjediste i sif djelatnosti



    @PutMapping("/{id}")
    public ResponseEntity<Firma> update(@PathVariable Integer id, @RequestBody Firma firma){
        return ResponseEntity.ok(firmaService.update(id, firma));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        firmaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}