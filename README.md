# Tetris

Prosjekt fra faget INF101 - Objektorientert programering ved universitetet i Bergen. Hensikten med prosjektet var å lage Tetris fra bunn på en måte som overholder prinsippene fra objekt orientert programering.  

## Oversikt over arkitektur

Vi baserer modellen vår på design -prinsippet om [model-view-controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller), hvor vi i størst mulig grad ønsker å skille fra hverandre
 - selve logikken og datastrukturene i modellen,
 - hvordan modellene *vises*, og
 - hvordan modellen *kontrolleres* av brukeren.

Dette leder oss til at vi deler inn koden vår i tre sentrale pakker: 
 - *inf101v22.tetris.model*
 - *inf101v22.tetris.view*
 - *inf101v22.tetris.controller*

I tillegg vil vi benytte oss av en datastruktur for *Grid* som er mer generell, og som skal være i pakken
 - *inf101.v22.grid*

Det hele kjøres fra `TetrisMain` i pakken *inf101v22.tetris*, som inneholder main-metoden. Foreløpig viser den bare en enkel gui med noen eksempeler på tilfeldige figurer, men når vi er ferdig starter vi Tetris main-metoden her.

### Modell

For å representere et spill med Tetris, er det to hoved-elementer vi må holde styr på:
 - et *brett* med *fliser*, og
 - en *fallende brikke*.

I tråd med prinsippene for objekt-orientert programmering identifiserer vi følgende klasser som egner seg for pakken *inf101v22.tetris.model*:
 - `TetrisModel` en klasse som representerer tilstanden til et komplett spill med Tetris. Denne klassen vil ha feltvariabler som representerer brettet med fliser og den fallende brikken, samt informasjon om spillet er game over.
 - `TetrisBoard` en klasse som representerer et brett med fliser. Dette er i bunn og grunn en variant av Grid som holder på fliser.
 - `Tile` en klasse som representerer en flis på brettet.
 - `Piece` er en klasse som representerer en tetris-brikke
 - `PositionedPiece` er en klasse som representerer en fallende brikke, og består i bunn og grunn av en enkelt brikke og dens posisjon.

 Modellen er på mange måter den viktigste delen av koden, og vi ønsker at koden som ligger i modellen er godt testet.

 ### Visning

 For å vise modellen grafisk, lar vi det det være en klasse `TetrisView` i pakken *inf101v22.tetris.view* som har som ansvar å tegne Tetris-modellen. For å tegne Tetris, er planen at TetrisView
  - først tegner Tetris-brettet, og
  - deretter tegner den fallende brikken "over" brettet.

TetrisView trenger å ha tilgang til modellen for å kunne tegne den, men vi ønsker at vi ikke skal uforvarende kunne *endre* modellen når vi gjør ting i TetrisView. For å innkapsle modellen vår, lar vi `TetrisViewable` være et grensesnitt i pakken *inf101v22.tetris.view* som beskriver hvilke metoder TetrisView behøver for å tegne et Tetris-brett. Så lar vi modellen TetrisModel implementere dette grensesnittet. TetrisView vil altså aldri vite at den (egentlig) jobber med en TetrisModel, den vet bare at den er en TetrisViewable.

### Kontroll

I pakken *inf101v22.tetris.controller* lar vi det være en klasse `TetrisController` som har som sitt ansvarsområde å endre modellen basert på input fra brukeren, samt styre ting som skjer av seg selv (slik som at brikken faller ned et hakk med jevne mellomrom).

På samme måte som for visningen, så er kontrolleren avhengig av tilgang til modellen. Samtidig vil vi innkapsle modellen så mye som mulig. Vi lar det derfor være et grensesnitt `TetrisControllable` i pakken *inf101v22.tetris.controller* som beskriver hvilke metoder kontrolleren trenger tilgang til, og så lar vi modellen vår TetrisModel implementere dette grensesnittet.
