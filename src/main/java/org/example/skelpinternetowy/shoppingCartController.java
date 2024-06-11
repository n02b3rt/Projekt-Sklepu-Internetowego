package org.example.skelpinternetowy;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class shoppingCartController {
    @FXML   Text teskt;

    @FXML
    void changeBeer(MouseEvent event) {
        teskt.setText("Piwo Piwo Kocham Piwo");
    }

}
