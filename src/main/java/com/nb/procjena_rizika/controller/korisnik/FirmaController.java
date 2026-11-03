package com.nb.procjena_rizika.controller.korisnik;


import com.nb.procjena_rizika.dto.request.FirmaRequestDto;
import com.nb.procjena_rizika.dto.response.FirmaResponseDto;
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
    public ResponseEntity<FirmaResponseDto> create(@RequestBody FirmaRequestDto firma){
        return new ResponseEntity<>(firmaService.create(firma), HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<FirmaResponseDto>> getAll(){
        return ResponseEntity.ok(firmaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FirmaResponseDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(firmaService.findById(id));
    }

    @GetMapping("/pib/{pib}")
    public ResponseEntity<FirmaResponseDto> findByPib(@PathVariable String pib){
        return ResponseEntity.ok(firmaService.findByPib(pib));
    }

    @GetMapping("/naziv/{naziv}")
    public ResponseEntity<List<FirmaResponseDto>> findByNaziv(@PathVariable String naziv){
        return ResponseEntity.ok(firmaService.findByNaziv(naziv));
    }
    @GetMapping("/sjediste/{sjediste}")
    public ResponseEntity<List<FirmaResponseDto>> findBySjediste(@PathVariable String sjediste){
        return ResponseEntity.ok(firmaService.findBySjediste(sjediste));
    }
    @GetMapping("/sifra/{sifra}")
    public ResponseEntity<List<FirmaResponseDto>> findBySifraDjelatnosti(@PathVariable String sifra){
        return ResponseEntity.ok(firmaService.findBySifraDjelatnosti(sifra));
    }



    @PutMapping("/{id}")
    public ResponseEntity<FirmaResponseDto> update(@PathVariable Integer id, @RequestBody FirmaRequestDto firmaRequestDto){
        return ResponseEntity.ok(firmaService.update(id, firmaRequestDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        firmaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}