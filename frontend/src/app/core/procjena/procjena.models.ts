export interface IndikatorRizika {
  id: number;
  naziv: string;
  opis: string;
  grupaFaktoraRizikaId: number;
  grupaFaktoraRizikaNaziv: string;
}

export interface IndikatorOcjenaRequest{
  indikatorRizikaId: number;
  ocjena: number;
}

export interface ProcjenaRizikaRequest{
  klijentPravnoLiceId: number;
  obrazlozenje: string | null;
  automatskiRizik: boolean;

  listaOcjena: IndikatorOcjenaRequest[];
}

export interface IndikatorOcjenaResponse{
  id: number;
  indikatorRizikaId: number;
  indikatorRizikaNaziv: string;

  ocjena: number;
}

export interface ProcjenaRizikaResponse{
  id: number;
  klijentPravnoLiceId: number;
  klijentPravnoLiceNaziv: string;

  korisnikKreatorId: number;
  korisnikKreatorUsername: string;

  ocjene: IndikatorOcjenaResponse[];

  prijetnja: number;
  ranjivost: number;
  posljedica: number;

  ukupniRizik: number;

  nivoRizikaId: number;
  nivoRizikaNaziv: string;

  datumProcjene: string;
  datumNaredneProcjene: string;

  automatskiRizik: boolean;

  obrazlozenje: string | null;
}
