# Projekt Sklep Internetowy

Projekt wykonywany w języku Java, na potrzeby zaliczenia przedmiotu "Programowanie Obiektowe" na Uniwersytecie
Rzeszowskim 2024

## Spis treści

1. [Opis projektu](#opis-projektu)
2. [Technologie](#technologie)
3. [Struktura projektu](#struktura-projektu)
4. [Instrukcja uruchomienia](#instrukcja-uruchomienia)
5. [Funkcjonalności](#funkcjonalności)

## Opis projektu

Sklep Internetowy to aplikacja umożliwiająca użytkownikom przeglądanie produktów, dodawanie ich do koszyka oraz
składanie zamówień. Projekt realizuje podstawowe funkcje e-commerce, takie jak rejestracja i logowanie użytkowników,
zarządzanie profilem, przeglądanie katalogu produktów oraz obsługa zamówień.

## Technologie

Projekt został zrealizowany przy użyciu następujących technologii:

- Java
- JavaFX (do interfejsu użytkownika)
- Hibernate (do zarządzania bazą danych)
- PostgreSQL (jako baza danych)
- Maven (do zarządzania zależnościami i budowania projektu)

## Struktura projektu

Projekt składa się z następujących pakietów i klas:

- `org.example.skelpininternetowy`
    - `SklepInternetowy.java`: Główna klasa aplikacji.
    - `HibernateUtil.java`: Klasa narzędziowa do zarządzania SessionFactory Hibernate.
    - `HomePageController.java`: Kontroler dla strony głównej.
- `org.example.skelpininternetowy.Hibernate`
    - `Klient.java`: Encja reprezentująca klienta.
    - `Produkt.java`: Encja reprezentująca produkt.
    - `Zamowienie.java`: Encja reprezentująca zamówienie.
    - `KlientDAO.java`: Klasa DAO do zarządzania klientami.
    - `ProduktDAO.java`: Klasa DAO do zarządzania produktami.
    - `ZamowienieDAO.java`: Klasa DAO do zarządzania zamówieniami.
- `org.example.skelpininternetowy.Pages`
    - `LoginController.java`: Kontroler dla strony logowania.
    - `RegisterController.java`: Kontroler dla strony rejestracji.
    - `UserPanelController.java`: Kontroler dla panelu użytkownika.
    - `singleProductController.java`: Kontroler dla widoku pojedynczego produktu.
    - `shoppingCartController.java`: Kontroler dla koszyka zakupowego.
- `org.example.skelpininternetowy.UI`
    - `MenuController.java`: Kontroler dla menu aplikacji.

## Instrukcja uruchomienia

Aby uruchomić projekt lokalnie, należy wykonać poniższe kroki:

1. Sklonuj repozytorium na swój komputer:
   ```bash
   git clone <URL_DO_REPOZYTORIUM>
2. Otwórz projekt w preferowanym IDE (np. IntelliJ IDEA).
3. Utwórz bazę "sklep_komputerowy" i skopiuj polecenia SQL do bazy danych które są w pliku "importDataBase.txt"
3. Skonfiguruj połączenie z bazą danych MySQL w pliku `hibernate.cfg.xml`.
4. Uruchom aplikację korzystając z klasy `SklepInternetowy.java`.

## Funkcjonalności

- **Rejestracja i logowanie**: Użytkownicy mogą tworzyć nowe konta oraz logować się na istniejące.
- **Przeglądanie produktów**: Użytkownicy mogą przeglądać dostępne produkty na stronie głównej.
- **Szczegóły produktu**: Użytkownicy mogą przeglądać szczegółowe informacje o wybranym produkcie.
- **Dodawanie do koszyka**: Zalogowani użytkownicy mogą dodawać produkty do koszyka.
- **Zarządzanie profilem**: Użytkownicy mogą edytować swoje dane profilowe.
- **Składanie zamówień**: Użytkownicy mogą składać zamówienia na produkty znajdujące się w koszyku.