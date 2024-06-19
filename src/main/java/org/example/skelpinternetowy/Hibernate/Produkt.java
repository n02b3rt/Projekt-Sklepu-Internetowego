package org.example.skelpinternetowy.Hibernate;

import javax.persistence.*;

/**
 * Klasa reprezentująca produkt w sklepie internetowym.
 */
@Entity
@Table(name = "produkty")
public class Produkt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produktu")
    private Integer idProduktu; // Identyfikator produktu

    @Column(name = "nazwa")
    private String nazwa; // Nazwa produktu

    @Column(name = "opis")
    private String opis; // Opis produktu

    @Column(name = "cena")
    private Float cena; // Cena produktu

    @Column(name = "urlzdjecia")
    private String urlZdjecia; // URL zdjęcia produktu

    /**
     * Pobiera identyfikator produktu.
     *
     * @return identyfikator produktu
     */
    public Integer getIdProduktu() {
        return idProduktu;
    }

    /**
     * Ustawia identyfikator produktu.
     *
     * @param idProduktu identyfikator produktu
     */
    public void setIdProduktu(Integer idProduktu) {
        this.idProduktu = idProduktu;
    }

    /**
     * Pobiera nazwę produktu.
     *
     * @return nazwa produktu
     */
    public String getNazwa() {
        return nazwa;
    }

    /**
     * Ustawia nazwę produktu.
     *
     * @param nazwa nazwa produktu
     */
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    /**
     * Pobiera opis produktu.
     *
     * @return opis produktu
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Ustawia opis produktu.
     *
     * @param opis opis produktu
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * Pobiera cenę produktu.
     *
     * @return cena produktu
     */
    public Float getCena() {
        return cena;
    }

    /**
     * Ustawia cenę produktu.
     *
     * @param cena cena produktu
     */
    public void setCena(Float cena) {
        this.cena = cena;
    }

    /**
     * Pobiera URL zdjęcia produktu.
     *
     * @return URL zdjęcia produktu
     */
    public String getUrlZdjecia() {
        return urlZdjecia;
    }

    /**
     * Ustawia URL zdjęcia produktu.
     *
     * @param urlZdjecia URL zdjęcia produktu
     */
    public void setUrlZdjecia(String urlZdjecia) {
        this.urlZdjecia = urlZdjecia;
    }
}
