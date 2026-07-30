package com.nb.procjena_rizika.controller.korisnik;


import com.nb.procjena_rizika.model.korisnik.Korisnik;
import com.nb.procjena_rizika.service.korisnik.KorisnikService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/korisnik")
@RequiredArgsConstructor
public class KorisnikController {


    private final KorisnikService korisnikService;
    //create
    @PostMapping
    public ResponseEntity<Korisnik> create(@RequestBody Korisnik korisnik) {
        return new ResponseEntity<>(korisnikService.create(korisnik), HttpStatus.CREATED);
    }


    //read
    @GetMapping("/{id}")
    public ResponseEntity<Korisnik> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(korisnikService.findById(id));
    }

    @GetMapping("/byname/{ime}")
    public ResponseEntity<List<Korisnik>> findByName(@PathVariable String ime) {
        return ResponseEntity.ok(korisnikService.findByIme(ime));
    }

    @GetMapping("/byusername/{username}")
    public ResponseEntity<Korisnik> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(korisnikService.findByUsername(username));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Korisnik>> findAll() {
        return ResponseEntity.ok(korisnikService.findAll());
    }

    //update

    @PutMapping("/{id}")
    public ResponseEntity<Korisnik> update(@PathVariable Integer id, @RequestBody Korisnik korisnik) {

        return ResponseEntity.ok(korisnikService.update(id, korisnik));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        korisnikService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
