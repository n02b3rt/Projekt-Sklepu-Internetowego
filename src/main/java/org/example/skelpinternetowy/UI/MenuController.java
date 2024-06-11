package org.example.skelpinternetowy.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class MenuController extends HBox{

    @FXML
    private HBox header;

    public MenuController(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("org/example/skelpinternetowy/UI/Menu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }

}
