package org.example.skelpinternetowy.Hibernate;

import javax.persistence.*;

@Entity
@Table(name = "produkty")
public class Produkt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produktu")
    private Long idProduktu;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "opis")
    private String opis;

    @Column(name = "cena")
    private Float cena;
    @Column(name = "urlzdjecia")
    private String urlZdjecia;

    // Getters and setters
    public Long getIdProduktu() {
        return idProduktu;
    }

    public void setIdProduktu(Long idProduktu) {
        this.idProduktu = idProduktu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Float getCena() {
        return cena;
    }

    public void setCena(Float cena) {
        this.cena = cena;
    }

    public String getUrlZdjecia() {
        return urlZdjecia;
    }

    public void setUrlZdjecia(String urlZdjecia) {
        this.urlZdjecia = urlZdjecia;
    }
}
