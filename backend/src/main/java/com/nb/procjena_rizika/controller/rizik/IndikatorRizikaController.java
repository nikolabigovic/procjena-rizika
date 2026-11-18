package com.nb.procjena_rizika.controller.rizik;

import com.nb.procjena_rizika.dto.response.IndikatorRizikaResponseDto;
import com.nb.procjena_rizika.model.rizik.ProcjenaRizika;
import com.nb.procjena_rizika.repository.rizik.IndikatorRizikaRepository;
import com.nb.procjena_rizika.service.rizik.IndikatorRizikaService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/indikator")

public class IndikatorRizikaController {
    private final IndikatorRizikaService indikatorRizikaService;

    @GetMapping("/all")
    public ResponseEntity<List<IndikatorRizikaResponseDto>> findAll() {
        return ResponseEntity.ok(indikatorRizikaService.findAll());
    }
}
