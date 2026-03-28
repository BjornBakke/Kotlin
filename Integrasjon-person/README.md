# Integrasjon-person

Spring Boot-applikasjon for personsøk.

## Forutsetninger

- Java 25
- Maven

## Starte applikasjonen

```bash
mvn spring-boot:run
```

### Stoppe applikasjonen

| Terminal | Forgrunn | Bakgrunn (`&`) |
|---|---|---|
| **PowerShell** | `Ctrl+C` | `Stop-Job 1` og `Remove-Job 1` |
| **Git Bash** | `Ctrl+C` | `kill %1` |
| **CMD** | `Ctrl+C` | `taskkill /F /PID <pid>` |

## Teste API-et

Når applikasjonen kjører, åpne nettleseren eller bruk curl:

```bash
curl http://localhost:8080/api/
```

Dette returnerer en liste med personer i JSON-format.
