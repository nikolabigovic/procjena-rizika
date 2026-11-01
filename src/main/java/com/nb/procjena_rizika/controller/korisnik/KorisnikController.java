package com.nb.procjena_rizika.controller.korisnik;


import com.nb.procjena_rizika.dto.request.KorisnikRequestDto;
import com.nb.procjena_rizika.dto.response.KorisnikResponseDto;
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
    public ResponseEntity<KorisnikResponseDto> create(@RequestBody KorisnikRequestDto korisnik) {
        return new ResponseEntity<>(korisnikService.create(korisnik), HttpStatus.CREATED);
    }


    //read
    @GetMapping("/{id}")
    public ResponseEntity<KorisnikResponseDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(korisnikService.findById(id));
    }

    @GetMapping("/byname/{ime}")
    public ResponseEntity<List<KorisnikResponseDto>> findByName(@PathVariable String ime) {
        return ResponseEntity.ok(korisnikService.findByIme(ime));
    }

    @GetMapping("/byusername/{username}")
    public ResponseEntity<KorisnikResponseDto> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(korisnikService.findByUsername(username));
    }

    @GetMapping("/all")
    public ResponseEntity<List<KorisnikResponseDto>> findAll() {
        return ResponseEntity.ok(korisnikService.findAll());
    }

    //update

    @PutMapping("/{id}")
    public ResponseEntity<KorisnikResponseDto> update(@PathVariable Integer id, @RequestBody KorisnikRequestDto korisnik) {

        return ResponseEntity.ok(korisnikService.update(id, korisnik));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        korisnikService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
