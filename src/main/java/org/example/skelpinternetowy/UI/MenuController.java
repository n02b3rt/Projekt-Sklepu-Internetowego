package org.example.skelpinternetowy.UI;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import org.example.skelpinternetowy.SklepInternetowy;

public class MenuController {

    @FXML
    public void zmianaScenyNaStroneGlowna(MouseEvent event) {
        SklepInternetowy.switchScene("/homePage.fxml");
    }
    @FXML
    public void zmianaScenyNaKoszyk(MouseEvent event) {
        if(SklepInternetowy.isLogin==true){
            SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/shoppingCart.fxml");
        }else{
            SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/LogIn.fxml");
        }

    }

    @FXML
    public void zmianaScenyNaProfil(MouseEvent event) {
        if(SklepInternetowy.isLogin==true){
            SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/userPanel.fxml");
        }else{
            SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/LogIn.fxml");
        }
    }

}
