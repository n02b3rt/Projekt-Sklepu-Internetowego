package org.example.skelpinternetowy.Hibernate;

import javax.persistence.*;

@Entity
@Table(name = "zamowienia")
public class Zamowienie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zamowienia")
    private Long idZamowienia;

    @ManyToOne
    @JoinColumn(name = "id_klienta")
    private Klient klient;

    @ManyToOne
    @JoinColumn(name = "id_produktu")
    private Produkt produkt;

    @Column(name = "ilosc")
    private Integer ilosc;

    // Getters and setters
    public Long getIdZamowienia() {
        return idZamowienia;
    }

    public void setIdZamowienia(Long idZamowienia) {
        this.idZamowienia = idZamowienia;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Produkt getProdukt() {
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
}
