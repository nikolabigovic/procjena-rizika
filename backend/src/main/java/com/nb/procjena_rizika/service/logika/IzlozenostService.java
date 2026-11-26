package com.nb.procjena_rizika.service.logika;

import com.nb.procjena_rizika.dto.response.IzlozenostResponseDto;
import com.nb.procjena_rizika.model.klijent.KlijentPravnoLice;
import com.nb.procjena_rizika.model.korisnik.Firma;
import com.nb.procjena_rizika.model.korisnik.Korisnik;
import com.nb.procjena_rizika.model.rizik.NivoRizika;
import com.nb.procjena_rizika.model.rizik.ProcjenaRizika;
import com.nb.procjena_rizika.repository.klijent.KlijentPravnoLiceRepository;
import com.nb.procjena_rizika.repository.korisnik.FirmaRepository;
import com.nb.procjena_rizika.repository.rizik.NivoRizikaRepository;
import com.nb.procjena_rizika.repository.rizik.ProcjenaRizikaRepository;
import com.nb.procjena_rizika.service.klijent.TrenutniKorisnikService;
import com.nb.procjena_rizika.service.rizik.ProcjenaRizikaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IzlozenostService {
    private final FirmaRepository firmaRepository;
    private final TrenutniKorisnikService trenutniKorisnikService;
    private final KlijentPravnoLiceRepository klijentPravnoLiceRepository;
    private final ProcjenaRizikaRepository procjenaRizikaRepository;
    private final ProcjenaRizikaService procjenaRizikaService;
    private final IzracunavanjeRizikaService izracunavanjeRizikaService;
    private final NivoRizikaRepository  nivoRizikaRepository;

    public IzlozenostResponseDto getIzlozenost(){
        IzlozenostResponseDto responseDto = new IzlozenostResponseDto();
        Firma firma= trenutniKorisnikService.getFirma();
        List<KlijentPravnoLice> klijenti = klijentPravnoLiceRepository.findAllByFirmaId(firma.getId());

        List<ProcjenaRizika> listaProcjena= new ArrayList<>();
        for (KlijentPravnoLice k : klijenti){
            Optional<ProcjenaRizika> procjena = procjenaRizikaRepository.findTopByKlijentPravnoLiceIdOrderByDatumProcjeneDesc(k.getId());
            if(procjena.isPresent()){
                listaProcjena.add(procjena.get()); //ovo get mora zato sto vraca optional ova metoda u repu
            }
        }
        Double ukupnaIzlozenost = 0.0;
        Integer brojSrednjeIVisokoRizicnih=0;
        Integer brojProcjenaKojeIsticu=0;
        if(listaProcjena.size()>0){
            for(ProcjenaRizika r : listaProcjena){
                ukupnaIzlozenost=ukupnaIzlozenost+ r.getUkupniRizik();
                if(r.getNivoRizika().getId().equals(4) ||  r.getNivoRizika().getId().equals(5)){
                    brojSrednjeIVisokoRizicnih++;
                }
                if(r.getDatumNaredneProcjene().isBefore(LocalDate.now().plusDays(30))){
                    brojProcjenaKojeIsticu++;
                }
            }
            responseDto.setUkupanProsjecniRizik(Math.round(ukupnaIzlozenost/listaProcjena.size()*10.0)/10.0);

            responseDto.setBrojKlijenataSaProcjenom(listaProcjena.size());
            responseDto.setBrojKlijenataBezProcjene(klijenti.size()-listaProcjena.size());

            NivoRizika nivoRizika = izracunavanjeRizikaService.klasifikacijaRizika(responseDto.getUkupanProsjecniRizik());
            responseDto.setNivoRizikaId(nivoRizika.getId());
            responseDto.setNivoRizikaNaziv(nivoRizika.getNaziv());

            responseDto.setBrojVisokoISrednjeVisokoRizicnih(brojSrednjeIVisokoRizicnih);
            responseDto.setBrojProcjenaKojeIsticu(brojProcjenaKojeIsticu);
            return responseDto;
        }
        else {//ako nema nijedna procjena onda ide u posebno nepoznato stanje, ukupni prosjecni rizik je null, a ostalo se setuje na 0 ili na full broj klijenata :D
            NivoRizika nepoznatoStanje=nivoRizikaRepository.findById(6).orElseThrow();
            responseDto.setNivoRizikaId(nepoznatoStanje.getId());
            responseDto.setNivoRizikaNaziv(nepoznatoStanje.getNaziv());

            responseDto.setBrojKlijenataBezProcjene(klijenti.size());
            responseDto.setBrojKlijenataSaProcjenom(0);
            responseDto.setBrojProcjenaKojeIsticu(0);
            responseDto.setBrojVisokoISrednjeVisokoRizicnih(0);

            return responseDto;
        }


    }
}
