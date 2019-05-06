**Travis CI :**   [![Build Status](https://travis-ci.org/skkriss/Messenger.svg?branch=master)](https://travis-ci.com/skkriss/Messenger)

**CodeClimate** [![Maintainability](https://api.codeclimate.com/v1/badges/7948b0d7814b7e7113cc/maintainability)](https://codeclimate.com/github/skkriss/Messenger/maintainability)

**REGUŁY ODDAWANIA PROJEKTU**

Wybieramy jedno z poniższych zadań. Zadania różnią się poziomem trudności i są inaczej punktowane.

Każdy projekt ma być wykonany przy użyciu narzędzia Maven lub Gradle w JUnit5! Projekt nie powinien zawierać pliku jar oraz folderu target lub build.

Obowiązkowo każdy projekt ma być podpięty pod serwis TravisCi oraz Codacy lub CodeClimate (badge ma być podpięty pod repozytorium).

Oddanie projektu będzie odbywać się poprzez utworzenie Issue w repozytorium (szybsza opcja) lub pokazaniu go na zajęciach.

Projekty, w których nie będzie przechodzić polecenie mvn test lub gradle test będą projektami zaliczonymi na 0%

Termin oddania pierwszego projektu to: 06.05.2019 (100% - oddajemy na zajęciach). Następny termin (13.05.2018) będzie terminem na 50%. Wówczas liczy się moment utworzenia Issue (każdy dzień zwłoki obniża wartość procentową o około 10%). 

Ponadto pod ocenę będzie brany styl projektu: jak zapisane są testy i jak sprawdzane są asercje.

Testy powinny wykorzystywać wiele różnych asercji (a nie tylko assertEquals)!

Sprawdzenie projektu na zajęciach będzie polegało na jego obronie: będą to krótkie pytania i ewentualne drobne zmiany w kodzie podane przez prowadzącego!

Prowadzący będzie sprawdzał projekty samodzielnie do dnia 04.05.2019.

Link do GitHub Classroom: https://classroom.github.com/a/1fjDZ_F6


------------------------

**Projekt 2 (14 pkt)**

Jesteś deweloperem piszącym fragment (bardzo uproszczonej) aplikacji (tutaj klasy Messenger) wysyłającej komunikaty do serwera. Twoja klasa korzysta z implementacji interfejsu MessageService. Zadaniem twojej aplikacji (uwaga: często spotykane w praktyce) jest m.in. ukrywanie statusów oraz wyjątków generowanych przez wykorzystywane komponenty takie jak MessageService. Zgodnie z życzeniem klienta twoja metoda odpowiedzialna za wysyłanie komunikatów ma zwracać liczby:

0 gdy powodzenie
1 gdy występują problemy z wysłaniem
2 gdy adres serwera lub komunikat jest niewłaściwie zbudowany Klasa Messenger powinna też dostarczać metodę do testowania połączenia z serwerem zwracającym liczby:
0 w przypadku sukcesu
1 w przeciwnym przypadku Szkielet systemu jest zaimplementowany w pliku https://stepik.org/media/attachments/lesson/207562/messenger.zip. Dokończ klasę Messenger, a następnie przy użyciu poznawanych technologii przeprowadź testy jednostkowe w tzw. izolacji (czyli bez gotowej implementacji MessageService)
Pod ocenę będą brane pod uwagę następujące elementy:

(0.5 pkt) Kompilacja i uruchomienie bezbłędne projektu + konfiguracja TravisCi.
(1.5 pkt) Uwzględnienie powyższych wymagań.
(3 pkt) Przypadki testowe (uwzględniające wyjątki).
(3 pkt) Przetestowanie przy użyciu ręcznie stworzonych atrap (co najmniej 6 testów, różnych od pozostałych)
(2 pkt) Przetestowanie przy użyciu Mockito (co najmniej 6 testów, różnych od pozostałych).
(2 pkt) Przetestowanie przy użyciu EasyMock (co najmniej 6 testów, różnych od pozostałych).
(0.5 pkt) Użycie różnych rodzaji atrap
(0.5 pkt) Pokrycie kodu (w przypadku ręcznie stworzonych atrap).
(1 pkt) Styl kodu.
Ponadto, jako punkty dodatkowe będą brane następujące elementy:

(2 pkt) Inne technologie dotyczące atrap, nie pokazywane na zajęciach (co najmniej po 5 testów każda z nich).
(1 pkt) Użycie GitFlow https://danielkummer.github.io/git-flow-cheatsheet/
Ponadto pod ocenę jest brane również: (Brak tych elementów: -1 pkt za podpunkt od obowiązkowej punktacji zadania!)

Historia projektu w repozytorium.
Różnorodne asercje (co najmniej 5 różnych).
Struktura projektu.