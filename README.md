# AML/CFT Risk Assessment System

Full-stack web aplikacija za automatizovanu procjenu rizika od pranja novca i finansiranja terorizma,
razvijena u skladu sa Zakonom o sprečavanju pranja novca i finansiranja terorizma (Crna Gora, 2023)
i smjernicama FOJ-a.

---

## O projektu

Sistem omogućava obveznicima zakona upravljanje klijentima (pravnim i fizičkim licima),
automatizovanu procjenu rizičnosti klijenata kroz 5 grupa faktora rizika, ponderisanje i
kategorizaciju klijenata, te automatsko određivanje roka naredne procjene na osnovu nivoa rizika.

### Kako se računa rizik

1. **Prijetnja**: korisnik ocjenjuje indikatore rizika. Ocjene se grupišu po grupama faktora
   (geografski, klijent, transakcija, proizvod/usluga, distributivni kanal), računa se prosjek po grupi,
   a grupe se ponderišu težinama koje zavise od **šifre djelatnosti** klijenta.
2. **Ranjivost**: preuzima se iz šifarnika na osnovu šifre djelatnosti klijenta.
3. **Posljedica**: definisana na nivou obveznika (firme) koja vrši procjenu.
4. **Ukupni rizik = prijetnja × ranjivost × posljedica**, klasifikovan u nivoe:

| Ukupni rizik | Nivo rizika | Naredna procjena |
|---|---|---|
| ≤ 5 | Niži rizik | za 12 mjeseci |
| 5 – 20 | Srednje niži rizik | za 12 mjeseci |
| 20 – 50 | Srednji rizik | za 6 mjeseci |
| 50 – 95 | Srednje visok rizik | za 3 mjeseca |
| 95 – 125 | Visok rizik | za 1 mjesec |

### Poslovna pravila

- Ako pravno lice ima povezano fizičko lice koje je **politički eksponirano lice (PEL)**,
  procjena se automatski označava kao visokorizična.
- Procjena se može ispraviti samo u roku od **7 dana** od kreiranja (u suprotnom `409 Conflict`).

---

## Tech Stack

**Backend:** Java 26, Spring Boot 4.1, Spring Security, JWT, Spring Data JPA / Hibernate,
Bean Validation, MapStruct, Lombok, Maven

**Baza:** MySQL 8

**Frontend:** Angular 22

---

## Arhitektura (backend)

Slojevita arhitektura: `controller → service → repository`.

- **DTO + MapStruct**: kontroleri primaju i vraćaju request/response DTO-ove, entiteti se ne izlažu
  direktno kroz API (npr. lozinka korisnika nikada ne ulazi u odgovor).
- **Sigurnost**: JWT autentifikacija (stateless), uloge `ADMIN` / `KORISNIK`, lozinke heširane (BCrypt).
- **Rukovanje greškama**: globalni `@RestControllerAdvice` sa jedinstvenim formatom greške
  (400 validacija, 401 neautentifikovan, 404 nije pronađeno, 409 konflikt).

---

## Struktura repozitorijuma

```
backend/    Spring Boot REST API
frontend/   Angular aplikacija
database/   schema.sql (struktura baze) i seed.sql (šifarnici)
```

---

## Pokretanje lokalno

**Preduslovi:** Java 26, Node.js, MySQL 8

**1. Baza**
```sql
CREATE DATABASE rizik_db;
```
Zatim učitaj, ovim redom, `database/schema.sql` pa `database/seed.sql`
(npr. kroz MySQL Workbench ili `mysql -u root -p rizik_db < database/schema.sql`).

**2. Backend**

Postavi environment varijable:
- `DB_PASSWORD`: lozinka za MySQL korisnika `root`
- `JWT_SECRET`: proizvoljan string od najmanje 32 karaktera

```bash
cd backend
./mvnw spring-boot:run
```
API je dostupan na `http://localhost:8080`.

**3. Frontend**
```bash
cd frontend
npm install
npx ng serve
```
Aplikacija je dostupna na `http://localhost:4200`.

**Prijava:** `POST /api/auth/login-jwt?rememberMe=false` sa `username` i `password` u tijelu zahtjeva.

---

## Status

- ✅ Baza podataka, entiteti i šifarnici
- ✅ REST API: CRUD za firme, korisnike, klijente i procjene
- ✅ Request/response DTO-ovi i MapStruct mapperi
- ✅ Poslovna logika: izračunavanje rizika end-to-end, ponderi iz baze, PEL pravilo, rok od 7 dana za ispravke
- ✅ Spring Security + JWT, uloge ADMIN/KORISNIK
- ✅ Globalno rukovanje greškama i validacija
- 🔄 Angular frontend: u toku
- ⏳ Planirano: unit testovi, Swagger/OpenAPI dokumentacija, Docker Compose, Flyway migracije

---

*Moj prvi full-stack projekat. Kod se kontinuirano unapređuje.*
