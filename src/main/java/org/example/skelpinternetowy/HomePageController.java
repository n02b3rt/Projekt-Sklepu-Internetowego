package org.example.skelpinternetowy;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.skelpinternetowy.Hibernate.Produkt;
import org.example.skelpinternetowy.Hibernate.ProduktDAO;
import org.example.skelpinternetowy.Pages.singleProductController;

import java.util.List;

import static org.example.skelpinternetowy.SklepInternetowy.switchScene;

/**
 * Kontroler dla strony głównej aplikacji SklepInternetowy.
 * Odpowiada za ładowanie i wyświetlanie produktów na stronie głównej.
 */
public class HomePageController {

    @FXML
    private GridPane productGrid;

    /**
     * Inicjalizuje kontroler i ładuje dane produktów.
     * Wywołuje metodę {@link #loadProductData()}, która pobiera i wyświetla produkty w GridPane.
     */
    @FXML
    public void initialize() {
        // Wywołuje metodę ładującą dane produktów
        loadProductData();
    }

    /**
     * Ładuje dane produktów z bazy danych i wyświetla je w GridPane.
     * Metoda tworzy instancję ProduktDAO do komunikacji z bazą danych,
     * następnie pobiera listę wszystkich produktów z bazy danych.
     * Jeśli lista produktów jest null, wyświetla komunikat o błędzie.
     * Jeśli lista produktów jest pusta, wyświetla odpowiedni komunikat.
     * Iteruje przez listę produktów i dla każdego produktu tworzy VBox zawierający informacje o produkcie,
     * który następnie dodaje do GridPane. Ustawia także wyrównanie i rozciąganie VBox w GridPane.
     */
    private void loadProductData() {
        // Tworzy instancję ProduktDAO do komunikacji z bazą danych
        ProduktDAO produktDAO = new ProduktDAO();
        // Pobiera listę wszystkich produktów z bazy danych
        List<Produkt> produkty = produktDAO.getAllProducts();

        // Sprawdza, czy lista produktów jest null
        if (produkty == null) {
            System.out.println("Lista produktów jest null.");
        } else if (produkty.isEmpty()) {
            // Sprawdza, czy lista produktów jest pusta
            System.out.println("Lista produktów jest pusta.");
        } else {
            int row = 0;
            int col = 0;
            // Iteruje przez listę produktów
            for (Produkt produkt : produkty) {
                // Tworzy VBox dla pojedynczego produktu
                VBox productBox = createProductBox(produkt);
                // Ustawia kursor na rękę przy najechaniu na produkt
                productBox.setCursor(Cursor.HAND);
                // Dodaje VBox do GridPane
                productGrid.add(productBox, col, row);

                // Ustawia wyrównanie VBox w GridPane
                GridPane.setHalignment(productBox, HPos.CENTER);
                GridPane.setValignment(productBox, VPos.CENTER);

                col++;
                // Zaczyna nowy wiersz po trzech kolumnach
                if (col == 3) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    /**
     * Tworzy VBox zawierający informacje o produkcie.
     * Metoda tworzy VBox na szczegóły produktu oraz ustawia obraz produktu w ImageView.
     * Następnie tworzy i stylizuje etykiety z nazwą i ceną produktu, które dodaje do VBox.
     * Ustawia także akcję kliknięcia na VBox, która ładuje nową scenę z informacjami o produkcie.
     *
     * @param produkt obiekt Produkt, który ma być wyświetlony
     * @return VBox zawierający informacje o produkcie
     */
    private VBox createProductBox(Produkt produkt) {
        VBox box = new VBox(); // Tworzy nowy VBox
        VBox detailsBox = new VBox(); // Tworzy VBox na szczegóły produktu
        box.setAlignment(Pos.CENTER); // Ustawia wyrównanie do środka

        // Tworzy pusty ImageView
        ImageView imageView = new ImageView();

        // Ustawia obraz dynamicznie na podstawie ścieżki
        String imagePath = produkt.getUrlZdjecia();
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            imageView.setImage(image); // Ustawia obraz w ImageView
        } catch (Exception e) {
            // Jeśli obraz nie zostanie znaleziony, używa domyślnego obrazu
            Image image = new Image(getClass().getResourceAsStream("/images/products/defaultPicture.png"));
            imageView.setImage(image); // Ustawia domyślny obraz w ImageView
        }

        imageView.setFitWidth(438); // Ustawia szerokość obrazu
        imageView.setPreserveRatio(true); // Zachowuje proporcje obrazu

        // Tworzy i stylizuje etykietę z nazwą produktu
        Label nazwaLabel = new Label(produkt.getNazwa());
        nazwaLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 30px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.05), 10, 0.5, 0, 0)");
        // Tworzy i stylizuje etykietę z ceną produktu
        Label cenaLabel = new Label(produkt.getCena() + " zł");
        cenaLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 18px; -fx-line-spacing: 100px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.05), 10, 0.5, 0, 0)");

        // Dodaje etykiety do detailsBox
        detailsBox.getChildren().addAll(nazwaLabel, cenaLabel);
        detailsBox.setAlignment(Pos.CENTER); // Ustawia wyrównanie do środka
        detailsBox.setStyle("-fx-padding: 15px 0 15px 0; -fx-background-color: " + SklepInternetowy.mainColor + "; -fx-pref-width: 438px;");

        // Dodaje ImageView i detailsBox do box
        box.getChildren().addAll(imageView, detailsBox);
        box.setStyle("-fx-pref-width: 438px; -fx-background-color: #f4f4f4; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.15), 10, 0.5, 0, 0)");

        // Ustawia akcję kliknięcia na box
        box.setOnMouseClicked(event -> {
            System.out.println("zmieniono na scene: " + "/org/example/skelpinternetowy/Page/singleProduct.fxml");
            try {
                // Załaduj header
                Parent header = FXMLLoader.load(getClass().getResource("/org/example/skelpinternetowy/UI/Menu.fxml"));

                // Załaduj główną zawartość
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/skelpinternetowy/Page/singleProduct.fxml"));
                Parent content = loader.load();

                // Uzyskaj kontroler dla nowej sceny
                singleProductController nowaInstacjaProduktu = loader.getController();

                // Ustaw produkt w kontrolerze
                nowaInstacjaProduktu.setProdukt(produkt);
                nowaInstacjaProduktu.loadProductData();

                // Utwórz główny layout
                VBox mainLayout = new VBox();
                mainLayout.getChildren().addAll(header, content);

                // Ustawienie VBox, aby wyśrodkować zawartość
                VBox.setVgrow(content, Priority.ALWAYS);
                mainLayout.setAlignment(Pos.CENTER);

                // Zmień scenę
                Stage stage = (Stage) box.getScene().getWindow();
                Scene scene = new Scene(mainLayout);
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return box; // Zwraca gotowy VBox z informacjami o produkcie
    }
}
