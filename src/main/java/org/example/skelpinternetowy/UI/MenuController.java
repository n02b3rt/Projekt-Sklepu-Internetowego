package org.example.skelpinternetowy.UI;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.example.skelpinternetowy.SklepInternetowy;

import java.io.IOException;

public class MenuController {

    @FXML
    public void zmianaScenyNaStroneGlowna(MouseEvent event) {
        SklepInternetowy.switchScene("/org/example/skelpinternetowy/homePage.fxml");
    }
    @FXML
    public void zmianaScenyNaKoszyk(MouseEvent event) {
        SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/shoppingCart.fxml");
    }

    @FXML
    public void zmianaScenyNaProfil(MouseEvent event) {
        SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/userPanel.fxml");
    }

}
