# Kotlin-spec forbedringsdesign

**Dato:** 2026-04-24

## Mål

Denne forbedringen skal gjøre `kotlin-spec` bedre som:

- læringsrepo for en person som vil forstå Kotlin stegvis
- praktisk oppslagsverk for senere bruk
- porteføljeprosjekt som fremstår bevisst, ryddig og faglig troverdig

## Nå-situasjon

Prosjektet har flere sterke sider allerede:

- bred tematisk dekning fra basis til avanserte Kotlin-emner
- hver fil har egen `main()` og kan kjøres isolert
- smoke-tester gir rask verifikasjon av at eksemplene fortsatt fungerer
- norsk språkføring gjør repoet personlig og konsistent

Samtidig finnes det svakheter som gjør læringsopplevelsen og porteføljeinntrykket svakere enn nødvendig:

- progresjonen mellom temaene er ikke tydelig nok i dokumentasjonen
- enkelte filer og filnavn fremstår midlertidige eller uferdige
- noen eksempler er gode teknisk, men ikke sterke nok pedagogisk
- repoet mangler en tydelig kuratert vei for hvordan det bør brukes som læringsløp
- det finnes sannsynlige hull i sentrale Kotlin-emner som er nyttige i praksis

## Designmål

Følgende mål styrer forbedringsarbeidet:

1. Hver pedagogisk fil skal ha et tydelig og avgrenset læringsformål.
2. Strukturen skal støtte anbefalt progresjon: `basis -> oop -> intermediate -> functional -> advanced`.
3. README skal fungere som både startside og navigasjonskart.
4. Nye emner skal bare legges til hvis de fyller faktiske læringshull.
5. Repoet skal bli mer presentabelt uten å miste sin enkle, kjørbare form.

## Foreslåtte endringer

### 1. Kuratere eksisterende innhold

Arbeidet skal starte med å forbedre kvaliteten på det som allerede finnes.

Tiltak:

- gå gjennom filer med svake eller tilfeldige navn og gi dem mer profesjonelle navn
- rydde opp i filer som bærer preg av stub, mellomtilstand eller svak pedagogisk verdi
- sikre at toppkommentarene faktisk forklarer hva filen dekker, når den brukes, typiske fallgruver og relevant Kotlin-dokumentasjon
- vurdere om enkelte eksempler bør forenkles eller gjøres mer realistiske for å bli lettere å lære av

Aktuelle kandidater for opprydding:

- `src/main/kotlin/Intermediate/exer.kt`
- `src/main/kotlin/Intermediate/Also?.kt`
- filer med inkonsekvent eller lite forklarende navngivning

### 2. Tydeligere læringsløp

Prosjektet skal bli lettere å bruke målrettet over tid.

Tiltak:

- forbedre `README.md` med en tydelig "start her"-seksjon
- beskrive anbefalt læringsrekkefølge mellom pakkene
- forklare hva hvert nivå trener og hvorfor rekkefølgen er nyttig
- gjøre det tydelig hvordan repoet kan brukes både til aktiv øving og som senere oppslagsverk

### 3. Målrettet nytt innhold

Det skal bare legges til nye filer når de gir tydelig merverdi.

Aktuelle temaer som bør vurderes høyt:

- generics
- data classes som eget tydelig tema dersom dagens dekning er for skjult
- mer praktisk nullability eller nullable collections
- tydeligere collections-transformasjoner dersom dagens dekning er spredt

Utvalgskriterier:

- temaet må være sentralt i vanlig Kotlin-bruk
- temaet må enten mangle eller være for svakt representert i dagens repo
- filen må kunne forklares med korte, tydelige, kjørbare eksempler

### 4. Styrke oppslagsverdi og porteføljeverdi

Repoet skal være lett å navigere når brukeren vil slå opp et konsept.

Tiltak:

- forbedre oversikter og beskrivelser i `README.md`
- sikre mer konsekvent språk, navngivning og pedagogisk format
- vurdere enkel indeks eller tydeligere tematisk oversikt hvis det øker nytten uten å gjøre repoet tungt

## Arkitektur og filansvar

Endringene bør i hovedsak konsentreres til disse områdene:

- `README.md`
  - hovedinngang til prosjektet
  - tydelig læringssti
  - forbedret navigasjon og begrunnelse for temaene

- `src/main/kotlin/basis`
  - beholde som grunnleggende introduksjon
  - vurdere om enkelte temaer bør presiseres eller utvides

- `src/main/kotlin/Intermediate`
  - rydde navn og innhold
  - gjøre nivået tydeligere og mer konsistent

- øvrige temapakker
  - selektive forbedringer i filer som er pedagogisk svake, uklare eller ujevne

- `src/test/kotlin/org/example/smoke`
  - justeres bare ved behov, primært for å sikre at nye eller omdøpte filer fortsatt dekkes av dagens testopplegg

## Testing og verifikasjon

Forbedringene skal verifiseres på to nivåer:

### Teknisk verifikasjon

- `mvn test` skal fortsatt passere
- nye eller omdøpte filer skal fortsatt kunne kjøres som forventet

### Pedagogisk verifikasjon

Det skal gjøres en eksplisitt egenkontroll med spørsmål som:

- gjør denne endringen repoet lettere å lære av?
- gjør denne endringen repoet lettere å bruke som oppslag?
- er ny kode faktisk nyttig, eller bare ekstra volum?
- ser navngivning og dokumentasjon bevisst og profesjonell ut?

## Avgrensning

Følgende er utenfor denne runden:

- stort kursopplegg med mange oppgaver og fasiter per fil
- eksterne biblioteker eller framework-spesifikke Kotlin-eksempler
- store arkitektoniske endringer som gjør repoet vanskeligere å forstå
- fyllinnhold som bare øker antall filer uten å øke læringsverdi

## Suksesskriterier

Arbeidet regnes som vellykket når:

- repoet fremstår ryddigere og mer gjennomtenkt
- README gir tydelig retning for hvordan prosjektet brukes
- svake eller tilfeldige filer er forbedret eller erstattet
- minst noen reelle læringshull er fylt med nytt, relevant innhold
- smoke-testene fortsatt passerer
- sluttresultatet føles nyttig for et menneske som faktisk skal lære Kotlin, ikke bare som en teknisk opprydding
