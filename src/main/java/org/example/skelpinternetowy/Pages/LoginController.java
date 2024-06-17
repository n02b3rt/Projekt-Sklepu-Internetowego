package org.example.skelpinternetowy.Pages;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.example.skelpinternetowy.Hibernate.Klient;
import org.example.skelpinternetowy.Hibernate.KlientDAO;
import org.example.skelpinternetowy.SklepInternetowy;

public class LoginController {
    @FXML
    private Button loginButton;

    @FXML
    private Text loginInfo;

    @FXML
    private TextField loginInput;

    @FXML
    private Button registerbtn;

    @FXML
    private PasswordField passwordInput;

    @FXML
    public void Zaloguj(MouseEvent event) {
        String login = loginInput.getText();
        String password = passwordInput.getText();

        Klient myKlient = new KlientDAO().getByNazwa(login);
        if(myKlient == null) {
            // nie znaleziono loginu w bazie
            loginInfo.setText("Nie znaleziono użytkownika o podanym loginie");
            return;
        }
        if(!myKlient.getHaslo().equals(password)) {
            loginInfo.setText("Niepoprawne hasło");
            return;
        }
        // zalogowano pomyślnie
        SklepInternetowy.isLogin = true;
        loginInfo.setText("Zalogowano pomyślnie");
        SklepInternetowy.switchScene("/homePage.fxml");
        SklepInternetowy.actualKlient = myKlient;
    }

    @FXML
    void zmianascenyregister(MouseEvent event) {
        SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/Register.fxml");
    }
}