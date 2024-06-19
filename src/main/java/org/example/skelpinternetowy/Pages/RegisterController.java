package org.example.skelpinternetowy.Pages;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.example.skelpinternetowy.Hibernate.Klient;
import org.example.skelpinternetowy.Hibernate.KlientDAO;
import org.example.skelpinternetowy.SklepInternetowy;

/**
 * Kontroler dla strony rejestracji użytkownika.
 * Odpowiada za rejestrowanie nowych użytkowników oraz walidację wprowadzonych danych.
 */
public class RegisterController {

    @FXML
    private TextField Name; // Pole tekstowe dla imienia

    @FXML
    private TextField Registerinfo; // Pole tekstowe dla informacji rejestracyjnych

    @FXML
    private TextField backBtn; // Przycisk powrotu

    @FXML
    private PasswordField Passwd; // Pole tekstowe dla hasła

    @FXML
    private Button RegisterBtn; // Przycisk rejestracji

    @FXML
    private Text registerinfo; // Tekst informacyjny o statusie rejestracji

    @FXML
    private Text registerinfo2; // Dodatkowy tekst informacyjny o statusie rejestracji

    @FXML
    private TextField Surname; // Pole tekstowe dla nazwiska

    @FXML
    private TextField adres; // Pole tekstowe dla adresu

    @FXML
    private TextField emailadr; // Pole tekstowe dla adresu email

    @FXML
    private TextField logIn; // Pole tekstowe dla loginu

    /**
     * Obsługuje kliknięcie przycisku rejestracji.
     * Sprawdza poprawność wprowadzonych danych i rejestruje nowego użytkownika.
     *
     * Funkcja obsługuje proces rejestracji nowego użytkownika. Najpierw pobiera dane
     * z pól tekstowych (imię, nazwisko, adres, email, login i hasło). Następnie sprawdza,
     * czy login jest już zajęty. Jeśli tak, wyświetla odpowiedni komunikat. Jeśli pola
     * nie są wypełnione lub hasło ma mniej niż 6 znaków, również wyświetla stosowne komunikaty.
     * W przeciwnym razie tworzy nowego klienta, zapisuje go w bazie danych i czyści pola tekstowe.
     *
     * Błędy wyłapywane przez funkcję:
     * - Login zajęty
     * - Niewypełnione pola
     * - Hasło krótsze niż 6 znaków
     *
     * @param event zdarzenie kliknięcia myszą
     */
    @FXML
    void Zarejestrujclick(MouseEvent event) {
        String name = Name.getText();
        String surname = Surname.getText();
        String address = adres.getText();
        String email = emailadr.getText();
        String login = logIn.getText();
        String password = Passwd.getText();

        // Sprawdza, czy login jest już zajęty
        Klient myKlient = new KlientDAO().getByNazwa(login);
        if (myKlient != null) {
            registerinfo2.setText("");
            registerinfo.setText("Login zajęty!");
            // Sprawdza, czy wszystkie pola są wypełnione
        } else if (name.isEmpty() || surname.isEmpty() || address.isEmpty() || email.isEmpty() || login.isEmpty() || password.isEmpty()) {
            registerinfo2.setText("");
            registerinfo.setText("Nie wypełniono wszystkich pól!");
            // Sprawdza, czy hasło ma odpowiednią długość
        } else if (password.length() < 6) {
            registerinfo2.setText("");
            registerinfo.setText("Hasło: minimum 6 znaków!");
        } else {
            // Rejestruje nowego użytkownika
            registerinfo.setText("");
            registerinfo2.setText("Utworzono konto!");
            Klient klient = new Klient();
            klient.setImie(name);
            klient.setNazwisko(surname);
            klient.setAdres(address);
            klient.setEmail(email);
            klient.setNazwa(login);
            klient.setHaslo(password);
            KlientDAO klientDAO = new KlientDAO();
            klientDAO.addKlient(klient);
            clearFields(); // Czyści pola tekstowe po rejestracji
        }
    }

    /**
     * Obsługuje kliknięcie przycisku powrotu.
     * Przełącza scenę na stronę logowania.
     *
     * @param event zdarzenie kliknięcia myszą
     */
    @FXML
    void Backclick(MouseEvent event) {
        SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/LogIn.fxml");
    }

    /**
     * Czyści wszystkie pola tekstowe.
     * Metoda ta jest wywoływana po pomyślnej rejestracji użytkownika, aby
     * usunąć dane wprowadzone do formularza i przygotować formularz na nową rejestrację.
     */
    private void clearFields() {
        Name.clear();
        Surname.clear();
        adres.clear();
        emailadr.clear();
        logIn.clear();
        Passwd.clear();
    }
}
