# Beershop back-end 

Op deze pagina vind je deel 1 van 2 om de Beershop applicatie te installeren. 

Voor het totaalplaatje is er een database bestand gevuld met diverse producten aanwezig om de functionaliteit te kunnen testen. 
Deze wordt standaard gebruikt bij het starten van de back-end server.

**Server of localhost** 
De app is ontwikkeld om op een server of stand-alone op een computer te kunnen draaien. 
Deze installatie is gebaseerd op gebruik op localhost, welke te bereiken valt via de standaard poort http://localhost:3000. 
(of poort 5000 in geval van een production build).

_**Back-end installatie**_

Om producten/gebruikers te tonen/bewerken heb je een database nodig naast de zichtbare vorm (front-end).
Als eerste stap installeer je **PostGreSQL 12** (of hoger) via https://www.postgresql.org/download/. 
De standaard instellingen kunnen blijven staan. Er zal wel gevraagd worden om het poortnummer. 
Aanpassen mag, houd er wel rekening mee dat de poort niet bezet mag zijn en dat andere programma's standaard op deze poort zijn ingesteld. 
Pas aan waar nodig.

Het wordt aanbevolen om een GUI database te gebruiken als je nog geen ervaring hebt met het werken met databases. Installeer bij voorkeur **PGAdmin**, 
speciaal voor **PostGreSQL**, deze vind je op https://www.pgadmin.org/download/, kies een installer voor het juiste systeem.  
Ook hier kan je de standaard aanbevolen instellingen aanhouden.

De applicatie kent twee hoofdmappen: "beershop" en "beershop-app". We beginnen met het installeren van de back-end map "beershop". Zorg dat je de IDE  Intellij hebt geïnstalleerd en navigeer naar de genoemde map,  dit doe je via  'File' > 'Open' > naam map. Wanneer je de map hebt geopend, zal  Intellij allereerst alles laden. Controleer als eerste de application.properties voor de juiste database configuratie:

    spring.jpa.database=postgresql
    spring.datasource.platform=postgres
    spring.datasource.url=jdbc:postgresql://localhost:5432/beershop
    spring.datasource.username=beershop
    spring.datasource.password=bs@$)(@))%
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.generate-ddl=true
    spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
    spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect
    spring.jpa.hibernate.ddl-auto=create
    spring.datasource.initialization-mode=always
    spring.http.multipart.max-file-size=10Mb
    spring.http.multipart.max-request-size=11MB

Zorg dus dat **PostGreSQL 12**(of 13) is geinstalleerd voordat je verder gaat, anders zal het systeem niet werken. Het systeem zal het '**data.sql**' gebruiken 
voor het vullen van de database. Wanneer er geen database aanwezig is, zal er een foutmelding volgen.

Als je alle stappen hebt gevolgd, kan je nu bovenaan de IDE op de 'play' icon klikken om de applicatie te starten. 
Wanneer alles succesvol is geladen,  kunnen de endpoints gebruikt worden op http://localhost:8080 (of ander ingestelde poort)

Om de data via de achterkant in te kunnen zien zonder front-end, dien je terug te gaan naar het browserscherm waar **PgAdmin** is geopend. 
Ververs je scherm om de nieuwe database 'beershop' te zien. Indien er gevraagd wordt om een username en password, 
dien je de gegevens in te vullen die in '**application.properties**' staan.

Standaard wordt bij elke start van de server het databestand opnieuw geladen en de huidige/bestaande database overschreven.  
Om verder te kunnen gaan met de bestaande data, open het application.properties bestand en pas de regel:

    spring.jpa.hibernate.ddl-auto=create

aan naar:

    spring.jpa.hibernate.ddl-auto=update

Nu wordt het databestand niet meer gebruikt en kan je verder met de huidige database. 
De data uit het data.sql bestand dient enkel ter voorbeeld en kan verwijderd worden via https://localhost:8080/cms/. 
Daar log je in met het administrator account.

[ga verder naar de front-end installatie] [https://github.com/rvelumos/beershop-front]
