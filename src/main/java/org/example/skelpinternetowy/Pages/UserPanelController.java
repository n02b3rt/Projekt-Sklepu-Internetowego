package org.example.skelpinternetowy.Pages;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.example.skelpinternetowy.Hibernate.Klient;
import org.example.skelpinternetowy.Hibernate.KlientDAO;
import org.example.skelpinternetowy.SklepInternetowy;

public class UserPanelController {

    @FXML
    private GridPane accionButtons;

    @FXML
    private Button cancleButton;

    @FXML
    private Button okButton;



    @FXML
    private Button changeButton;


    @FXML
    private TextField address;

    @FXML
    private TextField email;

    @FXML
    private TextField name;

    @FXML
    private TextField nick;

    @FXML
    private TextField password;

    @FXML
    private TextField surname;


    @FXML
    public void initialize() {
        address.setText(SklepInternetowy.actualKlient.getAdres());
        email.setText(SklepInternetowy.actualKlient.getEmail());
        name.setText(SklepInternetowy.actualKlient.getImie());
        nick.setText(SklepInternetowy.actualKlient.getNazwa());
        password.setText(SklepInternetowy.actualKlient.getHaslo());
        surname.setText(SklepInternetowy.actualKlient.getNazwisko());
    }

    @FXML
    void cancleChanges(MouseEvent event) {
        accionButtons.setVisible(false);
        changeButton.setVisible(true);
        showFields(false,SklepInternetowy.mainColor);
    }

    @FXML
    void changeData(MouseEvent event) {
        String changedname = name.getText();
        String changedsurname = surname.getText();
        String changedaddress = address.getText();
        String changedemail = email.getText();
        String changedlogin = nick.getText();
        String changedpassword = password.getText();

        boolean isLoginChanged = !changedlogin.equals(SklepInternetowy.actualKlient.getNazwa());
        KlientDAO klientDAO = new KlientDAO();
        Klient myKlient = isLoginChanged ? klientDAO.getByNazwa(changedlogin) : null;

        if (isLoginChanged && myKlient != null) {
            showAlert("Login zajęty!", "Wybrany login jest już zajęty, wybierz inny.");
        } else if (changedname.isEmpty() || changedsurname.isEmpty() || changedaddress.isEmpty() || changedemail.isEmpty() || changedlogin.isEmpty() || changedpassword.isEmpty()) {
            showAlert("Nie można edytować profilu!","Wszystkie pola informacji muszą być wypełnione.");
        } else if (changedpassword.length() < 6) {
            showAlert("Nie można edytować profilu!", "Hasło nie powinno mieć mniej niż 6 znaków.");
        } else {
            Klient klient = SklepInternetowy.actualKlient;
            klient.setImie(changedname);
            klient.setNazwisko(changedsurname);
            klient.setAdres(changedaddress);
            klient.setEmail(changedemail);
            klient.setNazwa(changedlogin);
            klient.setHaslo(changedpassword);
            klientDAO.editKlient(klient);
            showAlert("Gratulacje!","Dane klienta zostały zaktualizowane.");
            accionButtons.setVisible(false);
            changeButton.setVisible(true);
            showFields(false, SklepInternetowy.mainColor);
        }

    }
    @FXML
    void modifyData(MouseEvent event){
        accionButtons.setVisible(true);
        changeButton.setVisible(false);
        showFields(true, "#ffffff");

    }
    @FXML
    void logout(MouseEvent event){
        accionButtons.setVisible(true);
        changeButton.setVisible(false);
        SklepInternetowy.koszyk.clear();
        SklepInternetowy.isLogin=false;
        SklepInternetowy.switchScene("/homePage.fxml");
    }

    private void showFields(boolean stan, String colour){

        String backgroundColour = "-fx-background-color: "+colour+";";

        address.setEditable(stan);
        address.setStyle(backgroundColour);
        email.setEditable(stan);
        email.setStyle(backgroundColour);
        name.setEditable(stan);
        name.setStyle(backgroundColour);
        nick.setEditable(stan);
        nick.setStyle(backgroundColour);
        password.setEditable(stan);
        password.setStyle(backgroundColour);
        surname.setEditable(stan);
        surname.setStyle(backgroundColour);
    }

    private void showAlert(String messageheader,String message2content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText(messageheader);
        alert.setContentText(message2content);
        alert.showAndWait();
    }

}
