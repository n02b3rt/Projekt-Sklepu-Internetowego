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

public class RegisterController {

    @FXML
    private TextField Name;

    @FXML
    private TextField Registerinfo;

    @FXML
    private TextField backBtn;

    @FXML
    private PasswordField Passwd;

    @FXML
    private Button RegisterBtn;

    @FXML
    private Text registerinfo;

    @FXML
    private Text registerinfo2;

    @FXML
    private TextField Surname;

    @FXML
    private TextField adres;

    @FXML
    private TextField emailadr;

    @FXML
    private TextField logIn;

    @FXML
    void Zarejestrujclick(MouseEvent event) {
        String name = Name.getText();
        String surname = Surname.getText();
        String address = adres.getText();
        String email = emailadr.getText();
        String login = logIn.getText();
        String password = Passwd.getText();
        Klient myKlient = new KlientDAO().getByNazwa(login);
                if(myKlient!=null) {
                    registerinfo2.setText("");
                    registerinfo.setText("Login zajęty!");
                } else if (name.isEmpty() || surname.isEmpty() || address.isEmpty() || email.isEmpty() || login.isEmpty() || password.isEmpty()) {
                    registerinfo2.setText("");
                    registerinfo.setText("Nie wypełniono wszystkich pól!");

                } else if (password.length() < 6) {
                    registerinfo2.setText("");
                    registerinfo.setText("Hasło: minimum 6 znaków!");
                } else {

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
                    clearFields();
                }


    }

    @FXML
    void Backclick(MouseEvent event) {
        SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/LogIn.fxml");


    }


    private void clearFields() {
        Name.clear();
        Surname.clear();
        adres.clear();
        emailadr.clear();
        logIn.clear();
        Passwd.clear();
    }
}