package org.example.skelpinternetowy.Hibernate;

import javax.persistence.*;

@Entity
@Table(name = "klienci")
public class Klient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_klienta")
    private Integer idKlienta;

    @Column(name = "imie")
    private String imie;

    @Column(name = "nazwisko")
    private String nazwisko;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "haslo")
    private String haslo;

    @Column(name = "email")
    private String email;

    @Column(name = "adres")
    private String adres;

    public Klient(Integer idKlienta, String imie, String nazwisko, String nazwa, String haslo, String email, String adres) {
        this.idKlienta = idKlienta;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nazwa = nazwa;
        this.haslo = haslo;
        this.email = email;
        this.adres = adres;
    }

    public Klient() {

    }

    // Gettery i settery
    public Integer getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(Integer id_klienta) {
        this.idKlienta = id_klienta;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }


}
