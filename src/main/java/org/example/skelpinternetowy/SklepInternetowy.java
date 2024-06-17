package org.example.skelpinternetowy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import org.example.skelpinternetowy.Hibernate.Klient;

import java.io.IOException;

public class SklepInternetowy extends Application {
    private static Stage primaryStage;
    public static boolean isLogin = false;
    public static Klient actualKlient = new Klient();
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        try {
            // Wczytanie pliku CSS, widoku nagłówka (header), strony głównej
            String cssFile = getClass().getResource("/org/example/skelpinternetowy/styles/styleGlowne.css").toExternalForm();
            Parent header = FXMLLoader.load(getClass().getResource("/org/example/skelpinternetowy/UI/Menu.fxml"));
            Parent homePage = FXMLLoader.load(getClass().getResource("/homePage.fxml"));

            // Umieszczenie nagłówka i głównej sceny w VBox
            VBox root = new VBox(header, homePage);

            // Ustawienie VBox, aby wyśrodkować zawartość
            VBox.setVgrow(homePage, Priority.ALWAYS);
            root.setAlignment(Pos.CENTER);

            // Utworzenie sceny i dodanie stylów do sceny
            Scene scene = new Scene(root);
            scene.getStylesheets().add(cssFile);

            // Konfiguracja i wyświetlenie okna
            stage.setTitle("Sklep Internetowy");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) { // problem z wczytaniem pliku FXML
            e.printStackTrace(); // Wypisanie pełnego śladu stosu wyjątku.
            throw e;
        } catch (Exception e) { // Obejmuje to wszelkie wyjątki, które nie są specyficzne dla operacji wejścia/wyjścia.
            e.printStackTrace();
        }
    }

    public static void switchScene(String fxmlFile) {
        System.out.println("Uruchamia się");
        try {
            Parent newPage = FXMLLoader.load(SklepInternetowy.class.getResource(fxmlFile));

            // Pobranie obecnego VBox z sceny
            VBox root = (VBox) primaryStage.getScene().getRoot();

            // Zamiana drugiego dziecka VBox (scena główna)
            root.getChildren().set(1, newPage);
            VBox.setVgrow(newPage, Priority.ALWAYS); // Ustawienie właściwości VGrow dla nowej strony
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
