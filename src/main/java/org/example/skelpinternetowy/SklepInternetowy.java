package org.example.skelpinternetowy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SklepInternetowy extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Wczytanie pliku CSS, widoku nagłówka (header),głównej sceny
            String cssFile = getClass().getResource("/org/example/skelpinternetowy/styles/styleGlowne.css").toExternalForm();
            Parent header = FXMLLoader.load(getClass().getResource("/org/example/skelpinternetowy/UI/Menu.fxml"));
            Parent homePage = FXMLLoader.load(getClass().getResource("/org/example/skelpinternetowy/homePage.fxml"));

            // Umieszczenie nagłówka i głównej sceny w VBox
            VBox root = new VBox(header, homePage);

            // Utworzenie sceny i dodanie stylów do sceny
            Scene scene = new Scene(root);
            scene.getStylesheets().add(cssFile);

            // Konfiguracja i wyświetlenie okna
            stage.setTitle("Sklep Internetowy");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) { //  problem z wczytaniem pliku FXML
            e.printStackTrace(); // - Ta metoda wypisuje pełny ślad stosu wyjątku. Oznacza to, że wszystkie informacje o błędzie, w tym jego przyczyna i miejsce, w którym wystąpił, zostaną wypisane na konsolę.
            throw e;
        } catch (Exception e) { //  Obejmuje to wszelkie wyjątki, które nie są specyficzne dla operacji wejścia/wyjścia.
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
