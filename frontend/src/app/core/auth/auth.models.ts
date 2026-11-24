export interface LoginRequest {
  username: string;
  password: string;
}

export interface RegisterFirmaRequest{
  nazivFirme: string;
  sjediste: string;
  pib: string;
  sifraDjelatnosti: string;
  datumOsnivanja: string | null;
  telefon: string | null ;
  email: string;
  posljedice:number;
}

export interface RegisterKorisnikRequest{
  ime: string;
  prezime: string;
  username: string;
  email: string;
  password: string;

}

export interface RegisterRequest {
  firma:RegisterFirmaRequest;
  korisnik:RegisterKorisnikRequest;
}


export interface JwtResponse {
  token: string;
  refreshToken: string;
}

