package org.example.skelpinternetowy.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "zamowienia")
public class Zamowienie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zamowienia")
    private Integer idZamowienia;

    @Column(name = "nr_zamowienia")
    private Integer nrZamowienia;

    @ManyToOne
    @JoinColumn(name = "id_klienta")
    private Klient klient;

    @ManyToOne
    @JoinColumn(name = "id_produktu")
    private Produkt produkt;

    @Column(name = "ilosc")
    private Integer ilosc;

    public Zamowienie() {
    }

    public Zamowienie(Integer nrZamowienia, Klient klient, Produkt produkt, Integer ilosc, LocalDate dataZamowienia) {
        this.nrZamowienia = nrZamowienia;
        this.klient = klient;
        this.produkt = produkt;
        this.ilosc = ilosc;
        this.dataZamowienia = dataZamowienia;
    }

    @Column(name = "data_zamowienia")
    private LocalDate dataZamowienia;

    // Getters and setters
    public Integer getIdZamowienia() {
        return idZamowienia;
    }

    public void setIdZamowienia(Integer idZamowienia) {
        this.idZamowienia = idZamowienia;
    }

    public Integer getNrZamowienia() {
        return nrZamowienia;
    }

    public void setNrZamowienia(Integer nrZamowienia) {
        this.nrZamowienia = nrZamowienia;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Produkt getProdukt() { // Poprawna metoda zwracająca pojedynczy produkt
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }
    public LocalDate getDataZamowienia() {
        return dataZamowienia;
    }

    public void setDataZamowienia(LocalDate dataZamowienia) {
        this.dataZamowienia = dataZamowienia;
    }
}

