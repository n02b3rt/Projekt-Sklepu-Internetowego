package org.example.skelpinternetowy.Pages;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.example.skelpinternetowy.Hibernate.Klient;
import org.example.skelpinternetowy.Hibernate.KlientDAO;
import org.example.skelpinternetowy.SklepInternetowy;

/**
 * Kontroler dla strony logowania użytkownika.
 * Odpowiada za logowanie użytkowników oraz przełączanie scen.
 */
public class LoginController {

    @FXML
    private Button loginButton; // Przycisk do logowania

    @FXML
    private Text loginInfo; // Tekst informacyjny o statusie logowania

    @FXML
    private TextField loginInput; // Pole tekstowe do wprowadzania loginu

    @FXML
    private Button registerbtn; // Przycisk do przełączania na stronę rejestracji

    @FXML
    private PasswordField passwordInput; // Pole tekstowe do wprowadzania hasła

    /**
     * Obsługuje kliknięcie przycisku logowania.
     * Sprawdza poprawność wprowadzonych danych i loguje użytkownika.
     *
     * Funkcja obsługuje proces logowania użytkownika. Najpierw pobiera dane
     * z pól tekstowych (login i hasło). Następnie sprawdza, czy użytkownik
     * o podanym loginie istnieje w bazie danych. Jeśli użytkownik nie istnieje,
     * wyświetla komunikat o błędzie. Jeśli hasło jest niepoprawne, również
     * wyświetla odpowiedni komunikat. W przeciwnym razie ustawia użytkownika
     * jako zalogowanego, aktualizuje komunikat o pomyślnym logowaniu, przełącza
     * scenę na stronę główną oraz ustawia aktualnego klienta.
     *
     * Błędy wyłapywane przez funkcję:
     * - Nie znaleziono użytkownika o podanym loginie
     * - Niepoprawne hasło
     *
     * @param event zdarzenie kliknięcia myszą
     */
    public void Zaloguj() {
        String login = loginInput.getText();
        String password = passwordInput.getText();

        // Pobiera użytkownika z bazy danych na podstawie loginu
        Klient myKlient = new KlientDAO().getByNazwa(login);
        if (myKlient == null) {
            // Nie znaleziono loginu w bazie
            loginInfo.setText("Nie znaleziono użytkownika o podanym loginie");
            return;
        }
        if (!myKlient.getHaslo().equals(password)) {
            // Hasło niepoprawne
            loginInfo.setText("Niepoprawne hasło");
            return;
        }
        // Zalogowano pomyślnie
        SklepInternetowy.isLogin = true;
        loginInfo.setText("Zalogowano pomyślnie");
        SklepInternetowy.switchScene("/homePage.fxml"); // Przełącza scenę na stronę główną
        SklepInternetowy.actualKlient = myKlient; // Ustawia aktualnego klienta
    }

    /**
     * Obsługuje kliknięcie przycisku logowania.
     * Wywołuje metodę Zaloguj(), która zajmuje się procesem logowania użytkownika.
     *
     * @param event zdarzenie kliknięcia myszą
     */
    @FXML
    public void LoginButtonClicked(MouseEvent event) {
        Zaloguj();
    }

    /**
     * Obsługuje naciśnięcie klawisza Enter.
     * Wywołuje metodę Zaloguj(), jeśli naciśnięty klawisz to Enter, umożliwiając logowanie za pomocą klawiatury.
     *
     * @param event zdarzenie naciśnięcia klawisza
     */
    @FXML
    public void ListenForEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            Zaloguj();
        }
    }

    /**
     * Obsługuje kliknięcie przycisku przełączania na stronę rejestracji.
     *
     * Funkcja obsługuje przełączanie sceny na stronę rejestracji. Po kliknięciu
     * przycisku przełącza widok na stronę rejestracji użytkownika.
     *
     * @param event zdarzenie kliknięcia myszą
     */
    @FXML
    void zmianascenyregister(MouseEvent event) {
        SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/Register.fxml"); // Przełącza scenę na stronę rejestracji
    }
}
