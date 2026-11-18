# AML/CFT Risk Assessment

Aplikacija za procjenu rizika od pranja novca i finansiranja terorizma za klijente (pravna lica),
rađena po crnogorskom Zakonu o sprečavanju pranja novca i finansiranja terorizma i smjernicama FOJ-a.

Korisnik unese klijenta i povezana fizička lica, ocijeni 84 indikatora rizika, a aplikacija izračuna
nivo rizika i datum naredne procjene.

## Kako se računa rizik

Ukupni rizik = prijetnja × ranjivost × posljedica

- **prijetnja**: prosjek ocjena po 5 grupa faktora rizika, ponderisan težinama koje zavise od šifre djelatnosti klijenta
- **ranjivost**: iz šifarnika, po šifri djelatnosti
- **posljedica**: zadata na nivou firme koja radi procjenu

| Ukupni rizik | Nivo | Naredna procjena |
|---|---|---|
| ≤ 5 | Niži | 12 mjeseci |
| 5 – 20 | Srednje niži | 12 mjeseci |
| 20 – 50 | Srednji | 6 mjeseci |
| 50 – 95 | Srednje visok | 3 mjeseca |
| 95 – 125 | Visok | 1 mjesec |

Klijent automatski dobija visok rizik ako je neko od povezanih lica politički eksponirano, ako nema
upisanog stvarnog vlasnika, ako mu je šifra djelatnosti označena kao rizična, ili ako korisnik to ručno
označi uz obrazloženje. Procjena se može ispraviti samo u prvih 7 dana.

## Tehnologije

- Backend: Java 26, Spring Boot 4.1, Spring Security + JWT, Spring Data JPA, MapStruct, Lombok
- Frontend: Angular 22
- Baza: MySQL 8

```
backend/    Spring Boot REST API
frontend/   Angular aplikacija
database/   schema.sql, seed.sql (šifarnici), demo.sql (demo podaci)
```

## Pokretanje

Potrebni su Java 26, Node.js i MySQL 8.

Baza:
```sql
CREATE DATABASE rizik_db CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
```
```bash
mysql -u root -p rizik_db < database/schema.sql
mysql -u root -p rizik_db < database/seed.sql
mysql -u root -p rizik_db < database/demo.sql
```
Ako koristiš Workbench, prije pokretanja skripti izaberi `rizik_db` kao default schema, jer `schema.sql`
nema `USE` i briše postojeće tabele.

Backend (treba postaviti `DB_PASSWORD` i `JWT_SECRET`, min. 32 karaktera):
```bash
cd backend
./mvnw spring-boot:run
```

Frontend:
```bash
cd frontend
npm install
npx ng serve
```

Aplikacija je na `http://localhost:4200`, API na `http://localhost:8080`.

## Demo nalog

Ako je učitan `demo.sql`: **demo** / **demo123**

Tu su tri izmišljena klijenta, bez procjena, pa ih možeš napraviti sam:
jedan sa normalnim izračunom i dva koja automatski idu u visok rizik (PEP vlasnik i rizična djelatnost bez stvarnog vlasnika).

## Šta je urađeno, a šta ne

Radi: prijava, klijenti sa fizičkim licima, nova procjena, istorija procjena, nivo rizika i naredna procjena u listi klijenata.

Sljedeće: izloženost, admin panel, sređivanje UI-ja, unit testovi, Swagger, Docker Compose, Liquibase.

Ovo mi je prvi full-stack projekat, pa se kod još mijenja.
