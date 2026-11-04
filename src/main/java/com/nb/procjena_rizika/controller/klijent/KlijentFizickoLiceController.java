package com.nb.procjena_rizika.controller.klijent;

import com.nb.procjena_rizika.dto.request.KFLRequestDto;
import com.nb.procjena_rizika.dto.response.KFLResponseDto;
import com.nb.procjena_rizika.model.klijent.KlijentFizickoLice;
import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import com.nb.procjena_rizika.service.klijent.KlijentFizickoLiceService;
import com.nb.procjena_rizika.service.klijent.KlijentPravnoLiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/klijent/fl")
@RequiredArgsConstructor
public class KlijentFizickoLiceController {



    private final KlijentFizickoLiceService klijentFizickoLiceService;




    @GetMapping("/{id}")

    public ResponseEntity<KFLResponseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(klijentFizickoLiceService.findById(id));
    }

    @GetMapping("/jmbg/{jmbg}")

    public ResponseEntity<KFLResponseDto> getByJmbg(@PathVariable String jmbg) {
        return ResponseEntity.ok(klijentFizickoLiceService.findByMaticniBr(jmbg));
    }

    @GetMapping("/ime/{ime}")
    public ResponseEntity <List<KFLResponseDto>> getByIme(@PathVariable String ime) {
        List<KFLResponseDto> lista= klijentFizickoLiceService.findByIme(ime);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/prezime/{prezime}")
    public ResponseEntity< List<KFLResponseDto>> getByPrezime(@PathVariable String prezime) {
        List<KFLResponseDto> lista= klijentFizickoLiceService.findByPrezime(prezime);
        return ResponseEntity.ok(lista);
    }





    @PostMapping

    public ResponseEntity<KFLResponseDto> create(@Valid @RequestBody KFLRequestDto klijentFizickoLice) {
        return new ResponseEntity<>(klijentFizickoLiceService.create(klijentFizickoLice), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KFLResponseDto> update(@PathVariable Integer id, @RequestBody KFLRequestDto klijentFizickoLice) {
        return ResponseEntity.ok(klijentFizickoLiceService.update(id, klijentFizickoLice));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        klijentFizickoLiceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
