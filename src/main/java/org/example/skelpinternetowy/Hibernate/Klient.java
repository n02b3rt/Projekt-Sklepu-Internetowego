package org.example.skelpinternetowy.Hibernate;

import javax.persistence.*;

@Entity
@Table(name = "klienci")
public class Klient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_klienta")
    private Integer id_klienta;

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

    // Gettery i settery
    public Integer getId_klienta() {
        return id_klienta;
    }

    public void setId_klienta(Integer id_klienta) {
        this.id_klienta = id_klienta;
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
