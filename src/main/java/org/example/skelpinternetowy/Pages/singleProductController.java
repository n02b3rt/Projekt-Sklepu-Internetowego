package org.example.skelpinternetowy.Pages;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.example.skelpinternetowy.Hibernate.Produkt;
import org.example.skelpinternetowy.SklepInternetowy;
import javafx.scene.text.Text;

/**
 * Kontroler dla widoku pojedynczego produktu.
 * Odpowiada za wyświetlanie szczegółów produktu oraz dodawanie go do koszyka.
 */
public class singleProductController {
    private Produkt produkt;

    @FXML
    private Text cenaProduktu;
    @FXML
    private Button dodajDoKoszyka;
    @FXML
    private Text opisProduktu;
    @FXML
    private Text tytulStrony;
    @FXML
    private ImageView zdjeciaObrazka;
    @FXML
    private Text dodanoDoKoszyka;

    /**
     * Inicjalizuje kontroler i ukrywa komunikat o dodaniu do koszyka.
     */
    @FXML
    public void initialize() {
        // Ukrywa tekst informujący o dodaniu produktu do koszyka
        dodanoDoKoszyka.setVisible(false);
    }

    /**
     * Ładuje dane produktu i ustawia odpowiednie pola w widoku.
     * Metoda ustawia tytuł strony, opis oraz cenę produktu, a także
     * ustawia obraz produktu na podstawie podanego URL. Jeśli URL obrazu
     * jest pusty lub null, metoda wypisuje odpowiedni komunikat błędu.
     */
    public void loadProductData() {
        // Ustawia tytuł strony na nazwę produktu
        this.tytulStrony.setText(this.produkt.getNazwa());
        // Ustawia opis produktu
        this.opisProduktu.setText(this.produkt.getOpis());
        // Ustawia cenę produktu
        this.cenaProduktu.setText(String.valueOf(this.produkt.getCena()));

        // Jeśli ImageView dla zdjęcia produktu nie jest null
        if (this.zdjeciaObrazka != null) {
            try {
                String imageUrl = produkt.getUrlZdjecia();
                // Sprawdza, czy URL obrazu nie jest pusty ani null
                if (imageUrl != null && !imageUrl.trim().isEmpty()) {
                    // Załaduj zasób z katalogu resources
                    Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
                    this.zdjeciaObrazka.setImage(image);
                } else {
                    System.err.println("URL obrazu jest pusty lub null");
                }
            } catch (Exception e) {
                System.err.println("Błąd przy ustawianiu obrazu: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.err.println("zdjeciaObrazka is null");
        }
    }

    /**
     * Pobiera produkt.
     *
     * @return obiekt Produkt
     */
    public Produkt getProdukt() {
        return produkt;
    }

    /**
     * Ustawia produkt.
     *
     * @param produkt obiekt Produkt do ustawienia
     */
    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    /**
     * Dodaje produkt do koszyka lub przekierowuje do logowania, jeśli użytkownik nie jest zalogowany.
     * Metoda sprawdza, czy użytkownik jest zalogowany. Jeśli tak, produkt zostaje dodany do koszyka
     * i wyświetlany jest komunikat o pomyślnym dodaniu. Jeśli użytkownik nie jest zalogowany,
     * metoda przełącza scenę na stronę logowania.
     *
     * @param event zdarzenie kliknięcia myszą
     */
    @FXML
    void dodajDoKoszyka(MouseEvent event) {
        // Sprawdza, czy użytkownik jest zalogowany
        if (SklepInternetowy.isLogin) {
            // Dodaje produkt do koszyka
            SklepInternetowy.koszyk.add(this.produkt);
            // Wyświetla komunikat o dodaniu do koszyka
            dodanoDoKoszyka.setVisible(true);
        } else {
            // Przełącza scenę na stronę logowania
            SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/LogIn.fxml");
        }
    }
}
