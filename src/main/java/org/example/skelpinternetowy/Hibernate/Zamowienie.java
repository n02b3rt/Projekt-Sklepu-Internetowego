package org.example.skelpinternetowy.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Klasa reprezentująca zamówienie w sklepie internetowym.
 */
@Entity
@Table(name = "zamowienia")
public class Zamowienie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zamowienia")
    private Integer idZamowienia; // Identyfikator zamówienia

    @Column(name = "nr_zamowienia")
    private Integer nrZamowienia; // Numer zamówienia

    @ManyToOne
    @JoinColumn(name = "id_klienta")
    private Klient klient; // Klient składający zamówienie

    @ManyToOne
    @JoinColumn(name = "id_produktu")
    private Produkt produkt; // Produkt w zamówieniu

    @Column(name = "ilosc")
    private Integer ilosc; // Ilość produktu

    @Column(name = "data_zamowienia")
    private LocalDate dataZamowienia; // Data zamówienia

    /**
     * Domyślny konstruktor.
     */
    public Zamowienie() {
    }

    /**
     * Konstruktor inicjalizujący zamówienie.
     *
     * @param nrZamowienia numer zamówienia
     * @param klient klient składający zamówienie
     * @param produkt produkt w zamówieniu
     * @param ilosc ilość produktu
     * @param dataZamowienia data zamówienia
     */
    public Zamowienie(Integer nrZamowienia, Klient klient, Produkt produkt, Integer ilosc, LocalDate dataZamowienia) {
        this.nrZamowienia = nrZamowienia;
        this.klient = klient;
        this.produkt = produkt;
        this.ilosc = ilosc;
        this.dataZamowienia = dataZamowienia;
    }

    /**
     * Pobiera identyfikator zamówienia.
     *
     * @return identyfikator zamówienia
     */
    public Integer getIdZamowienia() {
        return idZamowienia;
    }

    /**
     * Ustawia identyfikator zamówienia.
     *
     * @param idZamowienia identyfikator zamówienia
     */
    public void setIdZamowienia(Integer idZamowienia) {
        this.idZamowienia = idZamowienia;
    }

    /**
     * Pobiera numer zamówienia.
     *
     * @return numer zamówienia
     */
    public Integer getNrZamowienia() {
        return nrZamowienia;
    }

    /**
     * Ustawia numer zamówienia.
     *
     * @param nrZamowienia numer zamówienia
     */
    public void setNrZamowienia(Integer nrZamowienia) {
        this.nrZamowienia = nrZamowienia;
    }

    /**
     * Pobiera klienta składającego zamówienie.
     *
     * @return klient składający zamówienie
     */
    public Klient getKlient() {
        return klient;
    }

    /**
     * Ustawia klienta składającego zamówienie.
     *
     * @param klient klient składający zamówienie
     */
    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    /**
     * Pobiera produkt w zamówieniu.
     *
     * @return produkt w zamówieniu
     */
    public Produkt getProdukt() {
        return produkt;
    }

    /**
     * Ustawia produkt w zamówieniu.
     *
     * @param produkt produkt w zamówieniu
     */
    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    /**
     * Pobiera ilość produktu.
     *
     * @return ilość produktu
     */
    public Integer getIlosc() {
        return ilosc;
    }

    /**
     * Ustawia ilość produktu.
     *
     * @param ilosc ilość produktu
     */
    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    /**
     * Pobiera datę zamówienia.
     *
     * @return data zamówienia
     */
    public LocalDate getDataZamowienia() {
        return dataZamowienia;
    }

    /**
     * Ustawia datę zamówienia.
     *
     * @param dataZamowienia data zamówienia
     */
    public void setDataZamowienia(LocalDate dataZamowienia) {
        this.dataZamowienia = dataZamowienia;
    }
}
