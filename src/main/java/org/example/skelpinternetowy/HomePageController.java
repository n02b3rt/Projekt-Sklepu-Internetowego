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

public class HomePageController {

    @FXML
    private GridPane productGrid;

    @FXML
    public void initialize() {
        loadProductData();
    }

    private void loadProductData() {
        ProduktDAO produktDAO = new ProduktDAO();
        List<Produkt> produkty = produktDAO.getAllProducts();

        if (produkty == null) {
            System.out.println("Lista produktów jest null.");
        } else if (produkty.isEmpty()) {
            System.out.println("Lista produktów jest pusta.");
        } else {
            int row = 0;
            int col = 0;
            for (Produkt produkt : produkty) {
                VBox productBox = createProductBox(produkt);
                productBox.setCursor(Cursor.HAND);
                productGrid.add(productBox, col, row);

                // Ustawienie wyrównania w komórkach GridPane
                GridPane.setHalignment(productBox, HPos.CENTER);
                GridPane.setValignment(productBox, VPos.CENTER);

                col++;
                if (col == 3) { // 3 produkty w jednym rzędzie
                    col = 0;
                    row++;
                }
            }
        }
    }

    private VBox createProductBox(Produkt produkt) {
        VBox box = new VBox();
        VBox detailsBox = new VBox();
        box.setAlignment(Pos.CENTER);

        // Utwórz pusty ImageView
        ImageView imageView = new ImageView();

        // Ustaw obraz dynamicznie
        String imagePath = produkt.getUrlZdjecia();
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            imageView.setImage(image);
        } catch (Exception e) {
            // Jeśli obraz nie zostanie znaleziony, użyj domyślnego obrazu
            Image image = new Image(getClass().getResourceAsStream("/images/products/defaultPicture.png"));
            imageView.setImage(image);
        }

        imageView.setFitWidth(438);
        imageView.setPreserveRatio(true);

        Label nazwaLabel = new Label(produkt.getNazwa());
        nazwaLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 30px; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.05), 10, 0.5, 0, 0)");
        Label cenaLabel = new Label( produkt.getCena()+ " zł");
        cenaLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 18px; -fx-line-spacing: 100px; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.05), 10, 0.5, 0, 0) ");

        detailsBox.getChildren().addAll(nazwaLabel, cenaLabel);
        detailsBox.setAlignment(Pos.CENTER);
        detailsBox.setStyle("-fx-padding: 15px 0 15px 0; -fx-background-color: " + SklepInternetowy.mainColor + "; -fx-pref-width: 438px;");

        box.getChildren().addAll(imageView, detailsBox);
        box.setStyle("-fx-pref-width: 438px; -fx-background-color: #f4f4f4; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.15), 10, 0.5, 0, 0)");

        box.setOnMouseClicked(event -> {
//            ta metoda jest tu, ponieważ funkcja loadProductData() nie mogła pobrać id z fxml ponieważ nie byl jeszcze zainicjalizowany?
            System.out.println("zmieniono na scene: " + "/org/example/skelpinternetowy/Page/singleProduct.fxml");
            try {
                // Załaduj header
                Parent header = FXMLLoader.load(getClass().getResource("/org/example/skelpinternetowy/UI/Menu.fxml"));

                // Załaduj główną zawartość
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/skelpinternetowy/Page/singleProduct.fxml"));
                Parent content = loader.load();

                // Uzyskaj kontroler
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
        return box;
    }
}