package org.example.skelpinternetowy.Pages;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.skelpinternetowy.Hibernate.Produkt;
import org.example.skelpinternetowy.Hibernate.ZamowienieDAO;
import org.example.skelpinternetowy.SklepInternetowy;

/**
 * Kontroler dla koszyka zakupowego.
 * Odpowiada za wyświetlanie produktów w koszyku oraz realizację zamówienia.
 */
public class shoppingCartController {
    private float cenaLacznie = 0; // Całkowita wartość koszyka

    @FXML
    private VBox produktyWKoszyku; // Kontener dla produktów w koszyku

    @FXML
    private Text lacznaWartoscKoszyka; // Tekst wyświetlający łączną wartość koszyka

    @FXML
    private Button submitButton; // Przycisk do zatwierdzenia zamówienia

    /**
     * Inicjalizuje kontroler i ładuje produkty do koszyka oraz ustawia łączną wartość.
     */
    @FXML
    public void initialize() {
        System.out.println("Koszyk działa");
        loadCheckoutProducts(); // Ładuje produkty do koszyka
        setCenaLacznie(); // Ustawia łączną wartość koszyka
    }

    /**
     * Ładuje produkty do koszyka i wyświetla je.
     * Sprawdza, czy koszyk nie jest pusty. Jeśli jest pusty, wyświetla komunikat.
     * Jeśli nie jest pusty, iteruje przez produkty w koszyku, tworzy dla nich HBox,
     * dodaje je do VBox i aktualizuje całkowitą cenę koszyka.
     */
    private void loadCheckoutProducts() {
        // Sprawdza, czy koszyk nie jest pusty
        if (!SklepInternetowy.koszyk.isEmpty()) {
            submitButton.setVisible(true); // Ustawia przycisk zatwierdzenia zamówienia jako widoczny
            int index = 0;
            // Iteruje przez produkty w koszyku
            for (Produkt produkt : SklepInternetowy.koszyk) {
                cenaLacznie += produkt.getCena(); // Dodaje cenę produktu do łącznej ceny
                HBox productBox = createProductBox(produkt, index); // Tworzy HBox dla produktu
                productBox.setStyle("-fx-background-color: #fff0de; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.5, 0, 0);");
                productBox.setAlignment(Pos.CENTER_LEFT);
                produktyWKoszyku.setSpacing(30);
                produktyWKoszyku.getChildren().addAll(productBox); // Dodaje HBox do kontenera produktów
                index++;
            }
        } else {
            submitButton.setVisible(false); // Ukrywa przycisk zatwierdzenia zamówienia
            Text text = new Text();
            text.setText("Nie ma produktów w koszyku");
            text.setStyle("-fx-font-size: 20px");
            produktyWKoszyku.getChildren().add(text); // Wyświetla informację o pustym koszyku
            System.out.println("Nie ma produktów");
        }
    }

    /**
     * Tworzy HBox dla produktu w koszyku.
     * Metoda tworzy HBox zawierający informacje o produkcie, takie jak jego obraz, nazwa i cena.
     * Dodaje również przycisk do usuwania produktu z koszyka. Jeśli obraz nie zostanie znaleziony,
     * używa domyślnego obrazu.
     *
     * @param produkt obiekt Produkt
     * @param index   indeks produktu w koszyku
     * @return HBox zawierający informacje o produkcie
     */
    private HBox createProductBox(Produkt produkt, int index) {
        HBox box = new HBox();
        box.setSpacing(30);
        box.setAlignment(Pos.CENTER);

        // Tworzy pusty ImageView dla zdjęcia produktu
        ImageView imageView = new ImageView();

        // Ustawia obraz dynamicznie
        String imagePath = produkt.getUrlZdjecia();
        System.out.println(imagePath);
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            imageView.setImage(image); // Ustawia obraz w ImageView
        } catch (Exception e) {
            // Jeśli obraz nie zostanie znaleziony, użyj domyślnego obrazu
            Image image = new Image(getClass().getResourceAsStream("/images/products/defaultPicture.png"));
            imageView.setImage(image);
        }

        // Tworzy przycisk usuwania produktu z koszyka
        Button usun = new Button("usuń");
        usun.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-cursor: hand;");
        usun.setOnMouseClicked(event -> {
            System.out.println("usun: " + produkt.getIdProduktu());
            SklepInternetowy.koszyk.remove(index); // Usuwa produkt z koszyka
            SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/shoppingCart.fxml"); // Odświeża scenę koszyka
        });

        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.15), 10, 0.5, 0, 0)");

        // Tworzy i stylizuje etykiety z nazwą i ceną produktu
        Label nazwaLabel = new Label(produkt.getNazwa());
        nazwaLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
        Label cenaLabel = new Label(produkt.getCena() + " zł");
        cenaLabel.setStyle("-fx-font-size: 18px; -fx-line-spacing: 100px; ");

        // Tworzy HBox dla nazwy produktu
        HBox nazwaBox = new HBox();
        nazwaBox.setStyle("-fx-pref-width: 150px");
        nazwaBox.setAlignment(Pos.CENTER);
        nazwaBox.getChildren().addAll(nazwaLabel);

        // Tworzy HBox dla ceny produktu
        HBox cenaBox = new HBox();
        cenaBox.setStyle("-fx-pref-width: 100px");
        cenaBox.setAlignment(Pos.CENTER);
        cenaBox.getChildren().addAll(cenaLabel);

        // Dodaje komponenty do głównego HBox
        box.getChildren().addAll(imageView, nazwaBox, cenaBox, usun);
        box.setStyle("-fx-padding: 0 0 15px 0; -fx-border-color: black; -fx-border-width: 1px; -fx-background-color: #f4f4f4;");

        return box; // Zwraca HBox z informacjami o produkcie
    }

    /**
     * Realizuje zamówienie produktów w koszyku.
     * Metoda dodaje zamówienie do bazy danych, czyści koszyk oraz przełącza scenę na panel użytkownika.
     *
     * @param event zdarzenie kliknięcia myszą
     */
    @FXML
    void zamowienieProduktow(MouseEvent event) {
        ZamowienieDAO zamowienie = new ZamowienieDAO();
        zamowienie.addZamowienie(SklepInternetowy.koszyk); // Dodaje zamówienie do bazy danych
        SklepInternetowy.koszyk.clear(); // Czyści koszyk
        SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/userPanel.fxml"); // Przełącza scenę na panel użytkownika
    }

    /**
     * Ustawia łączną wartość koszyka.
     * Metoda oblicza całkowitą wartość produktów w koszyku i wyświetla ją w odpowiednim polu tekstowym.
     */
    private void setCenaLacznie() {
        lacznaWartoscKoszyka.setText(String.valueOf(cenaLacznie) + " zł"); // Wyświetla łączną wartość koszyka
    }
}
