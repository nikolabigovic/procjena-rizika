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
