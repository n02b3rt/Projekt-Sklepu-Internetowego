package org.example.skelpinternetowy.Pages;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

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
    void cancleChanges(MouseEvent event) {

    }

    @FXML
    void changeData(MouseEvent event) {

    }

    @FXML
    void modifyData(MouseEvent event){
        accionButtons.setVisible(true);
        changeButton.setVisible(false);


        address.setEditable(true);
        address.setStyle("-fx-background-color: #ffffff;");
        email.setEditable(true);
        email.setStyle("-fx-background-color: #ffffff;");
        name.setEditable(true);
        name.setStyle("-fx-background-color: #ffffff;");
        nick.setEditable(true);
        nick.setStyle("-fx-background-color: #ffffff;");
        password.setEditable(true);
        password.setStyle("-fx-background-color: #ffffff;");
        surname.setEditable(true);
        surname.setStyle("-fx-background-color: #ffffff;");


    }

}
