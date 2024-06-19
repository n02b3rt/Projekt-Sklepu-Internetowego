package org.example.skelpinternetowy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import org.example.skelpinternetowy.Hibernate.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Główna klasa aplikacji SklepInternetowy, która rozszerza klasę Application z JavaFX.
 * Zarządza uruchamianiem aplikacji oraz przełączaniem scen.
 */
public class SklepInternetowy extends Application {
    private static Stage primaryStage;
    public static boolean isLogin = false;
    public static Klient actualKlient = new Klient();
    public static String mainColor = "#ff9f7b";

    public static List<Produkt> koszyk = new ArrayList<>();

    /**
     * Metoda start uruchamia główną scenę aplikacji.
     * Inicjalizuje główną scenę aplikacji, wczytuje niezbędne pliki FXML i CSS, oraz konfiguruje okno aplikacji.
     * Metoda przypisuje główną scenę (stage) do statycznego pola primaryStage.
     * Następnie ładuje plik CSS oraz dwa pliki FXML (Menu.fxml i homePage.fxml), które są używane do budowania interfejsu użytkownika.
     * Tworzy nowy kontener VBox i dodaje do niego nagłówek oraz stronę główną, ustawiając właściwości kontenera VBox, aby wyśrodkować zawartość i umożliwić rozciąganie strony głównej.
     * Tworzy nową scenę z kontenerem VBox i dodaje do niej styl CSS, konfiguruje ikonę i tytuł okna, ustawia scenę i wyświetla okno.
     * Obsługuje wyjątki IOException, które występują, jeśli jest problem z wczytaniem plików FXML lub CSS, oraz ogólne wyjątki, które mogą wystąpić podczas inicjalizacji aplikacji.
     *
     * @param stage główna scena aplikacji
     * @throws IOException jeśli wystąpi problem z wczytaniem plików FXML lub CSS
     */
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
            stage.getIcons().add(new Image(SklepInternetowy.class.getResourceAsStream("/images/Logo.png")));
            stage.setTitle("Sklep Komputerowy - Brzoskwinia.net");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) { // problem z wczytaniem pliku FXML
            e.printStackTrace(); // Wypisanie pełnego śladu stosu wyjątku.
            throw e;
        } catch (Exception e) { // Obejmuje to wszelkie wyjątki, które nie są specyficzne dla operacji wejścia/wyjścia.
            e.printStackTrace();
        }
    }

    /**
     * Metoda statyczna switchScene przełącza sceny w aplikacji.
     * Funkcja wypisuje komunikat na konsolę informujący o zmianie sceny,
     * wczytuje nowy plik FXML reprezentujący nową scenę, pobiera obecny kontener VBox ze sceny,
     * zamienia drugie dziecko kontenera VBox (scenę główną) na nową stronę,
     * oraz ustawia właściwość VGrow dla nowej strony, aby mogła się ona odpowiednio rozciągać.
     * Funkcja obsługuje wyjątek IOException, który może wystąpić podczas wczytywania nowego pliku FXML, wypisując pełny ślad stosu wyjątku.
     *
     * @param fxmlFile ścieżka do pliku FXML nowej sceny
     */
    public static void switchScene(String fxmlFile) {
        System.out.println("zmieniono na scene: " + fxmlFile);
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

    /**
     * Główna metoda uruchamiająca aplikację.
     * Wywołuje metodę launch z klasy Application, która uruchamia aplikację JavaFX.
     *
     * @param args argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        launch(args);
    }
}
