package org.example.skelpinternetowy.Pages;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.skelpinternetowy.Hibernate.Produkt;
import org.example.skelpinternetowy.Hibernate.ProduktDAO;
import org.example.skelpinternetowy.Hibernate.ZamowienieDAO;
import org.example.skelpinternetowy.SklepInternetowy;

import java.util.List;

public class shoppingCartController {
    private float cenaLacznie = 0;

    @FXML
    private VBox produktyWKoszyku;

    @FXML
    private Text lacznaWartoscKoszyka;

    @FXML
    private Button submitButton;

    @FXML
    public void initialize() {
        System.out.println("Koszyk dziala");
        loadCheckoutProducts();
        setCenaLacznie();
    }
    private void loadCheckoutProducts() {
        if(!SklepInternetowy.koszyk.isEmpty()) {
            submitButton.setVisible(true);
            int index = 0;
            for (Produkt produkt : SklepInternetowy.koszyk) {
                cenaLacznie += produkt.getCena();
                HBox productBox = createProductBox(produkt, index);
                productBox.setStyle("-fx-background-color: #fff0de; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.5, 0, 0);");
                productBox.setAlignment(Pos.CENTER_LEFT);
                produktyWKoszyku.setSpacing(30);
                produktyWKoszyku.getChildren().addAll(productBox);
                index++;
            }
        }else {
            submitButton.setVisible(false);
            Text text = new Text();
            text.setText("Nie ma produktów w koszyku");
            text.setStyle("-fx-font-size: 20px");
            produktyWKoszyku.getChildren().add(text);
            System.out.println("Nie ma produktów");
        }
    }

    private HBox createProductBox(Produkt produkt, int index) {
        HBox box = new HBox();
        box.setSpacing(30);
        box.setAlignment(Pos.CENTER);

        // Utwórz pusty ImageView
        ImageView imageView = new ImageView();

        // Ustaw obraz dynamicznie
        String imagePath = produkt.getUrlZdjecia();
        System.out.println(imagePath);
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            imageView.setImage(image);
        } catch (Exception e) {
            // Jeśli obraz nie zostanie znaleziony, użyj domyślnego obrazu
            Image image = new Image(getClass().getResourceAsStream("/images/products/defaultPicture.png"));
            imageView.setImage(image);
        }

        Button usun = new Button("usuń");
        usun.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-cursor: hand;");
        usun.setOnMouseClicked(event -> {
            System.out.println("usun:" + produkt.getIdProduktu());
            SklepInternetowy.koszyk.remove(index);
            SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/shoppingCart.fxml");
        });

        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        imageView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.15), 10, 0.5, 0, 0)");

        Label nazwaLabel = new Label(produkt.getNazwa());
        nazwaLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
        Label cenaLabel = new Label( produkt.getCena()+ " zł");
        cenaLabel.setStyle("-fx-font-size: 18px; -fx-line-spacing: 100px; ");

        HBox nazwaBox = new HBox();
        nazwaBox.setStyle("-fx-pref-width: 150px");
        nazwaBox.setAlignment(Pos.CENTER);
        nazwaBox.getChildren().addAll(nazwaLabel);

        HBox cenaBox = new HBox();
        cenaBox.setStyle("-fx-pref-width: 100px");
        cenaBox.setAlignment(Pos.CENTER);
        cenaBox.getChildren().addAll(cenaLabel);

        box.getChildren().addAll(imageView, nazwaBox, cenaBox, usun);
        box.setStyle("-fx-padding: 0 0 15px 0; -fx-border-color: black; -fx-border-width: 1px; -fx-background-color: #f4f4f4;");

        return box;
    }

    @FXML
    void zamowienieProduktow(MouseEvent event) {
        ZamowienieDAO zamowienie = new ZamowienieDAO();
        zamowienie.addZamowienie(SklepInternetowy.koszyk);
        SklepInternetowy.koszyk.clear();
        SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/userPanel.fxml");
    }

    private void setCenaLacznie(){
        lacznaWartoscKoszyka.setText(String.valueOf(cenaLacznie) + "zł");
    }

}
