package com.nb.procjena_rizika.controller.klijent;


import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import com.nb.procjena_rizika.service.klijent.KlijentPravnoLiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/klijent")
@RequiredArgsConstructor
public class KlijentPravnoLiceController {
    private final KlijentPravnoLiceService klijentPravnoLiceService;

    @GetMapping("/{id}")
    public ResponseEntity<KlijentPravnoLice> getById(@PathVariable Integer id){
        return ResponseEntity.ok(klijentPravnoLiceService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<KlijentPravnoLice>> getAll(){
        return ResponseEntity.ok(klijentPravnoLiceService.findAll());
    }

    @PostMapping
    public ResponseEntity<KlijentPravnoLice> create(@RequestBody KlijentPravnoLice klijentPravnoLice) {
        return new ResponseEntity<>(klijentPravnoLiceService.create(klijentPravnoLice), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KlijentPravnoLice> update(@PathVariable Integer id, @RequestBody KlijentPravnoLice klijentPravnoLice) {
        return ResponseEntity.ok(klijentPravnoLiceService.update(id,klijentPravnoLice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        klijentPravnoLiceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
