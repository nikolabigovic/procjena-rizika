-- Demo podaci za lokalno pokretanje (opciono).
-- Učitati POSLIJE schema.sql i seed.sql.
--
-- Demo nalog:  username: demo   lozinka: demo123
-- Svi podaci su izmišljeni.

SET NAMES utf8mb4;

-- Firma (obveznik) kojoj pripada demo korisnik.
-- posljedice se koristi u izračunu ukupnog rizika, mora biti popunjeno.
INSERT INTO `firma` (`id`, `naziv_firme`, `sjediste`, `pib`, `sifra_djelatnosti`, `datum_osnivanja_firme`, `telefon`, `email`, `posljedice`)
VALUES (1, 'Demo Obveznik d.o.o.', 'Podgorica', '00000001', '64.19', '2015-03-01', '+382 20 000 000', 'info@demo-obveznik.example', 4);

-- Demo korisnik (role 2 = KORISNIK), lozinka: demo123 (BCrypt)
INSERT INTO `korisnik` (`id`, `ime`, `prezime`, `username`, `email`, `password`, `firma_id`, `role_id`)
VALUES (1, 'Demo', 'Korisnik', 'demo', 'demo@demo-obveznik.example', '$2a$12$BhNsZFbu79t.bFaUk/L7j.7BzQoXsldjjZCPVmfetj6ACnUAms.f6', 1, 2);

-- Klijenti (pravna lica)
INSERT INTO `klijent_pravno_lice` (`id`, `naziv`, `sjediste`, `pib`, `sifra_djelatnosti`, `datum_osnivanja_firme`, `datum_uspostavljanja_poslovnog_odnosa`, `resident`, `razlog_poslovnog_odnosa`, `telefon`, `email`, `firma_id`) VALUES
  (1, 'Market Primjer d.o.o.',   'Nikšić',     '10000001', '47.11', '2012-05-10', '2024-01-15', 1, 'Otvaranje tekućeg računa',   '+382 40 000 001', 'kontakt@market.example',    1),
  (2, 'Transport Primjer d.o.o.', 'Bar',       '10000002', '49.41', '2018-09-20', '2025-03-01', 1, 'Kreditna linija',            '+382 30 000 002', 'kontakt@transport.example', 1),
  (3, 'Nekretnine Primjer d.o.o.', 'Budva',    '10000003', '68.31', '2021-02-01', '2026-06-10', 0, 'Platni promet sa inostranstvom', '+382 33 000 003', 'kontakt@nekretnine.example', 1);

-- Fizička lica povezana sa klijentima
-- status: 1 Stvarni vlasnik, 2 Direktor, 3 Osnivač, 4 Ovlašćeno lice
INSERT INTO `klijent_fizicko_lice` (`id`, `ime`, `prezime`, `maticni_br`, `datum_rodjenja`, `mjesto_prebivalista`, `pravno_lice_id`, `status_id`, `is_pel`) VALUES
  (1, 'Marko',  'Primjerović', '0101980210001', '1980-01-01', 'Nikšić',    1, 1, 0),
  (2, 'Ana',    'Primjerović', '0202985215002', '1985-02-02', 'Nikšić',    1, 2, 0),
  (3, 'Petar',  'Testić',      '0303975210003', '1975-03-03', 'Bar',       2, 1, 1),
  (4, 'Jelena', 'Testić',      '0404990215004', '1990-04-04', 'Podgorica', 3, 2, 0);
