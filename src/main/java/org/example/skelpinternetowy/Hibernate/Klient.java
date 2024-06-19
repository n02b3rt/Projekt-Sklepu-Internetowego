package org.example.skelpinternetowy.Hibernate;

import javax.persistence.*;

/**
 * Klasa reprezentująca klienta w sklepie internetowym.
 */
@Entity
@Table(name = "klienci")
public class Klient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_klienta")
    private Integer idKlienta; // Identyfikator klienta

    @Column(name = "imie")
    private String imie; // Imię klienta

    @Column(name = "nazwisko")
    private String nazwisko; // Nazwisko klienta

    @Column(name = "nazwa")
    private String nazwa; // Login klienta

    @Column(name = "haslo")
    private String haslo; // Hasło klienta

    @Column(name = "email")
    private String email; // Email klienta

    @Column(name = "adres")
    private String adres; // Adres klienta

    /**
     * Konstruktor inicjalizujący klienta.
     *
     * @param idKlienta identyfikator klienta
     * @param imie imię klienta
     * @param nazwisko nazwisko klienta
     * @param nazwa login klienta
     * @param haslo hasło klienta
     * @param email email klienta
     * @param adres adres klienta
     */
    public Klient(Integer idKlienta, String imie, String nazwisko, String nazwa, String haslo, String email, String adres) {
        this.idKlienta = idKlienta;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nazwa = nazwa;
        this.haslo = haslo;
        this.email = email;
        this.adres = adres;
    }

    /**
     * Domyślny konstruktor.
     */
    public Klient() {
    }

    /**
     * Pobiera identyfikator klienta.
     *
     * @return identyfikator klienta
     */
    public Integer getIdKlienta() {
        return idKlienta;
    }

    /**
     * Ustawia identyfikator klienta.
     *
     * @param idKlienta identyfikator klienta
     */
    public void setIdKlienta(Integer idKlienta) {
        this.idKlienta = idKlienta;
    }

    /**
     * Pobiera imię klienta.
     *
     * @return imię klienta
     */
    public String getImie() {
        return imie;
    }

    /**
     * Ustawia imię klienta.
     *
     * @param imie imię klienta
     */
    public void setImie(String imie) {
        this.imie = imie;
    }

    /**
     * Pobiera nazwisko klienta.
     *
     * @return nazwisko klienta
     */
    public String getNazwisko() {
        return nazwisko;
    }

    /**
     * Ustawia nazwisko klienta.
     *
     * @param nazwisko nazwisko klienta
     */
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    /**
     * Pobiera login klienta.
     *
     * @return login klienta
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Ustawia login klienta.
     *
     * @param nazwa login klienta
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * Pobiera hasło klienta.
     *
     * @return hasło klienta
     */
    public String getHaslo() {
        return haslo;
    }

    /**
     * Ustawia hasło klienta.
     *
     * @param haslo hasło klienta
     */
    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    /**
     * Pobiera email klienta.
     *
     * @return email klienta
     */
    public String getEmail() {
        return email;
    }

    /**
     * Ustawia email klienta.
     *
     * @param email email klienta
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Pobiera adres klienta.
     *
     * @return adres klienta
     */
    public String getAdres() {
        return adres;
    }

    /**
     * Ustawia adres klienta.
     *
     * @param adres adres klienta
     */
    public void setAdres(String adres) {
        this.adres = adres;
    }
}
