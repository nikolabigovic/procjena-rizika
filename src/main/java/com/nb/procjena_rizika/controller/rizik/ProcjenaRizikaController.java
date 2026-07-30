package com.nb.procjena_rizika.controller.rizik;


import com.nb.procjena_rizika.model.korisnik.Firma;
import com.nb.procjena_rizika.model.rizik.ProcjenaRizika;
import com.nb.procjena_rizika.service.korisnik.FirmaService;
import com.nb.procjena_rizika.service.rizik.ProcjenaRizikaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/firma")
@RequiredArgsConstructor
public class ProcjenaRizikaController {

    private final ProcjenaRizikaService procjenaRizikaService;


    @PostMapping
    public ResponseEntity<ProcjenaRizika> save(@RequestBody ProcjenaRizika procjenaRizika){
        return new ResponseEntity<>(procjenaRizikaService.create(procjenaRizika), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProcjenaRizika> findById(@PathVariable Integer id){
        return ResponseEntity.ok(procjenaRizikaService.findById(id));
    }

}
