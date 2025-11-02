# RMAS 2025 - Zadaća 1 (Meetup developera)

## Opis projekta
Ovaj program prati učesnike meetup događaja – programere. Svaki programer ima ime, prezime, godine iskustva, oznaku zemlje i listu programskih jezika koje koristi. Programeri su podijeljeni u dvije grupe: **BackendDeveloper** i **FrontendDeveloper**, a svaka grupa ima dodatni podatak o frameworku koji koristi.

Cilj programa je:
- prikazati listu programera,
- prebrojati koliko programera koristi svaki jezik,
- izračunati prosječno iskustvo po jeziku,
- filtrirati programere po frameworku.

## Struktura projekta
- **Interfejs Osoba**: definiše dvije metode:
  - `punIdentitet()` – vraća ime i prezime programera,
  - `zemljaPorijekla()` – vraća oznaku zemlje programera.

- **Osnovna klasa Programer**:  
  Sadrži zajedničke osobine svih programera i provjere da se ne mogu kreirati objekti sa praznim ili neispravnim podacima.  
  - Validacija uključuje provjeru da ime, prezime i oznaka zemlje nisu prazni, da godine iskustva nisu negativne i da lista jezika nije prazna.
  - Lista jezika se normalizira na mala slova i čini se **immutable** za ostatak programa.

- **Klase BackendDeveloper i FrontendDeveloper**:  
  Nasljeđuju **Programer** i dodaju svojstvo frameworka (`backendFramework` ili `frontendFramework`).

## Korištene funkcije/metode

- **`brojProgrameraPoJezikuManual`**  
  Prebrojava koliko programera koristi svaki programski jezik pomoću petlji i `mutableMapOf`.

- **`brojProgrameraPoJezikuGroup`**  
  Radi isto što i manualna verzija, ali koristi funkcije **flatMap** i **groupingBy**, što čini kod kraćim i preglednijim.

- **`prosjecnoIskustvoPoJezikuManual`**  
  Računa prosječno iskustvo po jeziku koristeći dvije mape: `suma` za zbir godina iskustva i `broj` za broj programera koji koriste taj jezik.

- **`prosjecnoIskustvoPoJezikuGroup`**  
  Isto što i manualna verzija, ali koristi **flatMap**, **groupBy** i **mapValues** za jednostavniji i kraći kod.

- **`filtrirajPoFrameworku`**  
  Filtrira programere prema zadanom frameworku.  
  - Za **BackendDevelopere** provjerava `backendFramework`.
  - Za **FrontendDevelopere** provjerava `frontendFramework`.

- **`ispisProgramera`**  
  Ispisuje sve programere u konzolu sa njihovim osnovnim informacijama:
  - ime i prezime,
  - tip developera (backend/frontend),
  - lista jezika,
  - framework (ako je primjenjivo).

## Pokretanje programa
1. Otvori Kotlin projekt u IntelliJ IDEA ili sličnom IDE-u.
2. Napravi novu Kotlin datoteku i zalijepi kod `main.kt`.
3. Pokreni funkciju `main`. Program će:
   - prikazati sve programere,
   - prebrojati i ispisati broj programera po jeziku (manualno i pomoću funkcija za grupisanje),
   - izračunati prosječno iskustvo po jeziku (manualno i grupisano),
   - filtrirati programere po frameworku "Spring Boot" i ispisati rezultat.

## Ispis u konzoli
Primjer ispisanih podataka za programere:

Adnan Kurtic — Backend developer — jezici: kotlin, java — framework: Spring Boot
Emina Smajic — Frontend developer — jezici: javascript, kotlin — framework: React
Maja Stankovic — Backend developer — jezici: java, kotlin — framework: Ktor
Luka Markovic — Frontend developer — jezici: typescript, javascript — framework: Vue.js
Nina Ibrahimovic — Backend developer — jezici: java, scala — framework: Spring Boot


Zatim slijedi:
- Broj programera po jeziku (manualno i grupisano),
- Prosječno iskustvo po jeziku (manualno i grupisano),
- Filtrirani programeri prema frameworku.

## Upotreba AI alata
Tokom pravljenja zadatka koristila sam **ChatGPT** da dobijem savjet kako napisati `check` provjere u funkciji `main`, bacanje (throw) pogresaka ukoliko su objekti prazni , te za ispravku grešaka koje su mi se javljale prilikom kodiranja a vezane su za izracun prosjecnog iskustva po jeziku.

## Provjere u programu
U funkciji `main` dodane su tri provjere:
1. Svi programeri imaju barem jedan jezik.
2. Backend developeri imaju unesen framework.
3. Frontend developeri imaju unesen framework.

## Napomena
- Prosječno iskustvo po jeziku je tipa `Double`.
- Lista jezika je **immutable** izvana (`List<String>`), dok se unutra koristi `mutableListOf` za obradu podataka.



