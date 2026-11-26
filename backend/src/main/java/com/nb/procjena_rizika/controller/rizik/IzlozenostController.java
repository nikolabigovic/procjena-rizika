package com.nb.procjena_rizika.controller.rizik;

import com.nb.procjena_rizika.dto.response.IzlozenostResponseDto;
import com.nb.procjena_rizika.service.logika.IzlozenostService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/izlozenost")
public class IzlozenostController {

    private final IzlozenostService izlozenostService;

    @GetMapping("/dashboard")
    public ResponseEntity<IzlozenostResponseDto> getDashboard(){
        return ResponseEntity.ok(izlozenostService.getIzlozenost());
    }
}
