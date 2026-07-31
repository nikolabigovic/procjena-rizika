package com.nb.procjena_rizika.controller.rizik;


import com.nb.procjena_rizika.model.rizik.IndikatorOcjena;
import com.nb.procjena_rizika.model.rizik.IndikatorRizika;
import com.nb.procjena_rizika.service.rizik.IndikatorOcjenaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pr/{procjena_id}/ocjena")
@RequiredArgsConstructor
public class IndikatorOcjenaController {

    private final IndikatorOcjenaService indikatorOcjenaService;

    @PostMapping
    public ResponseEntity<IndikatorOcjena> create(@RequestBody IndikatorOcjena indikatorOcjena){
        return new ResponseEntity<>(indikatorOcjenaService.create(indikatorOcjena), HttpStatus.CREATED);
    }



    @GetMapping("/{rizik}")
    public ResponseEntity<List<IndikatorOcjena>> findByIndikator(@PathVariable Integer rizik){
        return ResponseEntity.ok(indikatorOcjenaService.findByIndikatorId(rizik));

    }

    @PutMapping("/{id}")
    public ResponseEntity<IndikatorOcjena> update(@PathVariable Integer id, @RequestBody IndikatorOcjena indikatorOcjena){
        return ResponseEntity.ok(indikatorOcjenaService.update(id, indikatorOcjena));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        indikatorOcjenaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
