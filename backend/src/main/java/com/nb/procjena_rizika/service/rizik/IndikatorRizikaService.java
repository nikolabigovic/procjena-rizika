package com.nb.procjena_rizika.service.rizik;

import com.nb.procjena_rizika.dto.response.IndikatorRizikaResponseDto;
import com.nb.procjena_rizika.mapper.IndikatorRizikaMapper;
import com.nb.procjena_rizika.model.rizik.IndikatorRizika;
import com.nb.procjena_rizika.repository.rizik.IndikatorRizikaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndikatorRizikaService {

    private final IndikatorRizikaRepository indikatorRizikaRepository;
    private final IndikatorRizikaMapper indikatorRizikaMapper;

    public List<IndikatorRizikaResponseDto> findAll() {
        return indikatorRizikaMapper.toResponse(indikatorRizikaRepository.findAll());
    }
}
