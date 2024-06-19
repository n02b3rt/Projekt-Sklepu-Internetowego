package org.example.skelpinternetowy.UI;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.example.skelpinternetowy.SklepInternetowy;

/**
 * Kontroler dla menu aplikacji SklepInternetowy.
 * Odpowiada za obsługę nawigacji pomiędzy różnymi scenami.
 */
public class MenuController {

    /**
     * Przełącza scenę na stronę główną.
     * Ta metoda jest wywoływana, gdy użytkownik kliknie przycisk odpowiadający
     * za przejście do strony głównej. Metoda wywołuje funkcję switchScene z
     * klasy SklepInternetowy, przekazując ścieżkę do pliku FXML strony głównej.
     *
     * @param event zdarzenie kliknięcia myszą
     */
    @FXML
    public void zmianaScenyNaStroneGlowna(MouseEvent event) {
        // Przełącza scenę na homePage.fxml
        SklepInternetowy.switchScene("/homePage.fxml");
    }

    /**
     * Przełącza scenę na koszyk, jeśli użytkownik jest zalogowany, w przeciwnym razie na stronę logowania.
     * Ta metoda jest wywoływana, gdy użytkownik kliknie przycisk odpowiadający
     * za przejście do koszyka. Jeśli użytkownik jest zalogowany (isLogin == true),
     * metoda wywołuje funkcję switchScene z klasy SklepInternetowy, przekazując
     * ścieżkę do pliku FXML koszyka. W przeciwnym razie przekazuje ścieżkę do pliku
     * FXML strony logowania.
     *
     * @param event zdarzenie kliknięcia myszą
     */
    @FXML
    public void zmianaScenyNaKoszyk(MouseEvent event) {
        if (SklepInternetowy.isLogin) {
            // Przełącza scenę na shoppingCart.fxml, jeśli użytkownik jest zalogowany
            SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/shoppingCart.fxml");
        } else {
            // Przełącza scenę na LogIn.fxml, jeśli użytkownik nie jest zalogowany
            SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/LogIn.fxml");
        }
    }

    /**
     * Przełącza scenę na profil użytkownika, jeśli użytkownik jest zalogowany, w przeciwnym razie na stronę logowania.
     * Ta metoda jest wywoływana, gdy użytkownik kliknie przycisk odpowiadający
     * za przejście do profilu użytkownika. Jeśli użytkownik jest zalogowany (isLogin == true),
     * metoda wywołuje funkcję switchScene z klasy SklepInternetowy, przekazując
     * ścieżkę do pliku FXML profilu użytkownika. W przeciwnym razie przekazuje
     * ścieżkę do pliku FXML strony logowania.
     *
     * @param event zdarzenie kliknięcia myszą
     */
    @FXML
    public void zmianaScenyNaProfil(MouseEvent event) {
        if (SklepInternetowy.isLogin) {
            // Przełącza scenę na userPanel.fxml, jeśli użytkownik jest zalogowany
            SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/userPanel.fxml");
        } else {
            // Przełącza scenę na LogIn.fxml, jeśli użytkownik nie jest zalogowany
            SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/LogIn.fxml");
        }
    }
}
