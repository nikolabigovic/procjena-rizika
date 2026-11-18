export interface KlijentPravnoLice {
  id: number;
  naziv: string;
  sjediste: string;
  pib: string;
  sifraDjelatnosti: string;
  datumOsnivanjaFirme: string;
  datumUPO: string;
  resident: boolean;
  razlogPoslovnogOdnosa: string;
  telefon: string;
  email: string;
  firmaId:number;
}
export interface KlijentPravnoLiceRequest{
  naziv: string;
  sjediste: string;
  pib: string;
  sifraDjelatnosti: string;
  datumOsnivanjaFirme: string | null;
  datumUPO: string | null;
  resident: boolean;
  razlogPoslovnogOdnosa: string;
  telefon: string;
  email: string;
}

export interface KlijentFizickoLiceRequest {
  ime: string;
  prezime: string;
  maticniBr: string;
  datumRodjenja: string;
  mjestoPrebivalista: string;
  isPel: boolean;
  pravnoLiceId:number;
  statusId:number;
}

export interface KlijentFizickoLiceResponse {
  id: number;
  ime: string;
  prezime: string;
  maticniBr: string;
  datumRodjenja: string;
  mjestoPrebivalista: string;
  isPel: boolean;
  pravnoLiceId:number;
  statusId:number;

}

export const STATUSI_FIZICKOG_LICA = [
  { id: 1, naziv: 'Stvarni vlasnik' },
  { id: 2, naziv: 'Direktor' },
  { id: 3, naziv: 'Osnivač' },
  { id: 4, naziv: 'Ovlašćeno lice' },
];
