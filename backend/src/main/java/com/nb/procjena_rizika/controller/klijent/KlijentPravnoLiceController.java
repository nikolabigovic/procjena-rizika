package com.nb.procjena_rizika.controller.klijent;


import com.nb.procjena_rizika.dto.request.KPLRequestDto;
import com.nb.procjena_rizika.dto.response.KPLResponseDto;
import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import com.nb.procjena_rizika.service.klijent.KlijentPravnoLiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/klijent/pl")
@RequiredArgsConstructor
public class KlijentPravnoLiceController {
    private final KlijentPravnoLiceService klijentPravnoLiceService;

    @PostMapping

    public ResponseEntity<KPLResponseDto> create(@Valid  @RequestBody KPLRequestDto klijentPravnoLice) {
        return new ResponseEntity<>(klijentPravnoLiceService.create(klijentPravnoLice), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KPLResponseDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(klijentPravnoLiceService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<KPLResponseDto>> getAll(){
        return ResponseEntity.ok(klijentPravnoLiceService.findAll());
    }

    @GetMapping("/pib/{pib}")
    public ResponseEntity <KPLResponseDto> getByPib(@PathVariable String pib){
        return ResponseEntity.ok(klijentPravnoLiceService.findByPib(pib));
    }

    @GetMapping ("/sjediste/{sjediste}")
    public ResponseEntity<List<KPLResponseDto>> getBySjediste(@PathVariable String sjediste){
        return ResponseEntity.ok(klijentPravnoLiceService.findBySjediste(sjediste));
    }

    @GetMapping("/sifra/{sifra}")
    public ResponseEntity<List<KPLResponseDto>> getBySifra(@PathVariable String sifra){
        return ResponseEntity.ok(klijentPravnoLiceService.findBySifraDjelatnosti(sifra));
    }







    @PutMapping("/{id}")
    public ResponseEntity<KPLResponseDto> update(@PathVariable Integer id, @RequestBody KPLRequestDto klijentPravnoLice) {
        return ResponseEntity.ok(klijentPravnoLiceService.update(id,klijentPravnoLice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        klijentPravnoLiceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
