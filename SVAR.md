For å fullføre laben, ber vi deg om å svare på følgende spørsmål. Svar på spørsmålene ved å fylle ut denne filen.

## Hva syntes du om denne semesteroppgaven? Har du forslag til hvordan den kan gjøres bedre eller enklere?

Alt i alt tenker jeg at semesteroppgaven var passe utfordrene. Det var relativt enkelt å følge stegene i guiden, men
oppgaven stilte forsatt krav til den enkeltes kunnskaper/ferdigheter. Det ville vært hjelpsomt om oppgaven innkluderte relevante 
kilder/lenker så man kunne fortløpende lese seg opp på de aktuelle teamene underveis i guiden. 

## Hvor i koden din benytter du deg av gjenbruk av kode? Er det noen steder du føler du ikke klarte å gjenbruke kode på en god måte?

Jeg benytter meg av gjenbruk av kode i flere av klassene. Dette har jeg blant annet gjort med å lage hjelpemetoder av kode jeg ellers ville repetert, jeg har benyttet meg av mange hjelpemetodet blant annet i Tetrismodell-klassen. I TetrisView-klassen gjennbruker jeg kode med å bruke static metodene fra GraphicalHelperMethods-klassen. I tilleg vil jeg si at jeg gjennbruker koden i Grid klassene ved å la TetrisBoard "extende" Grid-klassen. Jeg føler at jeg ikke klarte å gjennbruke kode på en god måte i TetrisContoller-klassen hvor jeg endte opp med en svært mange if-setninger inne i KeyPressed-metoden.


## Hvilke grep gjør vi for å øke modulariteten i koden? Gi noen eksempeler.

Først og fremst arkitekturen til koden basert på design-prinsippet model-view-contoller. Denne arkitekturen separer funksjonaliteten til koden i tre relativt uavhengige moduler. 
-Modellen administerer datastrukturen og logikken/reglene til programmet
-View tegner selve teris spillet
-Controller reagerer på bruker-input og oppdaterer modellen
Siden mesteparten av logikken og datastrukturen ligger i model-pakken trenger view og conroller pakken tilgang til kode fra modell pakken for å kunne utfore deres oppgaver. For å beholde modulariteten i koden lager vi interfaces (TetrisConrollable og TetrisViewable) som implementeres av TetrisModell klassen. I disse interfacene definerer vi kun de metodene vi trenger for å hente ut nødvendig informasjon fra model-pakken; i viewable-interfacet definerer vi metoder for å hente informasjon om brettet, Tetris brikken og annen informasjon vi trenger for å tegne brettet, mens i controllable-interfacet definrer vi metoder som tillater konrolleren å oppdatere modellen når f.eks. brukeren av programmen trkker på en knapp. 

Andre grep vi gjør for å øke modulariteten er å begrense andres tilgang til vår kode ved å bruke private og final nøkkelordene på feltvariabler og metoder hvor det er fornuftig å begrense tilgangen. Med andre ord; kun bruke puvlic nøkkelordet der det er nødvendig.