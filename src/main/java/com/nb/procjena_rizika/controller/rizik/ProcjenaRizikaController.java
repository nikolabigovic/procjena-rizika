package com.nb.procjena_rizika.controller.rizik;


import com.nb.procjena_rizika.dto.request.ProcjenaRizikaRequestDto;
import com.nb.procjena_rizika.dto.response.ProcjenaRizikaResponseDto;
import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import com.nb.procjena_rizika.model.korisnik.Firma;
import com.nb.procjena_rizika.model.korisnik.Korisnik;
import com.nb.procjena_rizika.model.rizik.ProcjenaRizika;
import com.nb.procjena_rizika.service.korisnik.FirmaService;
import com.nb.procjena_rizika.service.rizik.ProcjenaRizikaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pr")
@RequiredArgsConstructor
public class ProcjenaRizikaController {

    private final ProcjenaRizikaService procjenaRizikaService;


    @PostMapping
    public ResponseEntity<ProcjenaRizikaResponseDto> save(@Valid @RequestBody ProcjenaRizikaRequestDto procjenaRizikaRequestDto, Authentication authentication) {

        return new ResponseEntity<>(procjenaRizikaService.create(procjenaRizikaRequestDto, authentication.getName()), HttpStatus.CREATED);
    }





    @GetMapping("/{id}")
    public ResponseEntity<ProcjenaRizikaResponseDto> findById(@PathVariable Integer id){
        return ResponseEntity.ok(procjenaRizikaService.findById(id));
    }

    @GetMapping("/kor/{korisnikId}")
    public ResponseEntity <List<ProcjenaRizikaResponseDto>> findByKor(@PathVariable Integer korisnikId){
        return ResponseEntity.ok(procjenaRizikaService.findByKorisnik(korisnikId));
    }

    @GetMapping("/kl/{klijent}")
    public ResponseEntity<List<ProcjenaRizikaResponseDto>>  findByKlijent(@PathVariable Integer klijent){
        return ResponseEntity.ok(procjenaRizikaService.findByKlijent(klijent));
    }

    @GetMapping("/nivo/{nivo}")
    public ResponseEntity<List<ProcjenaRizikaResponseDto>> findByNivo(@PathVariable Integer nivo){
        return ResponseEntity.ok(procjenaRizikaService.findByNivoRizikaId(nivo));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProcjenaRizika> update(@PathVariable Integer id, @RequestBody ProcjenaRizika procjena){
        return ResponseEntity.ok(procjenaRizikaService.update(id, procjena));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        procjenaRizikaService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
