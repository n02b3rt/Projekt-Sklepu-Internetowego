package org.example.skelpinternetowy.Pages;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.example.skelpinternetowy.Hibernate.Produkt;
import org.example.skelpinternetowy.HomePageController;
import org.example.skelpinternetowy.SklepInternetowy;
import javafx.scene.text.Text;

public class singleProductController {
    private Produkt produkt;

    @FXML
    private Text cenaProduktu;
    @FXML
    private Button dodajDoKoszyka;
    @FXML
    private Text opisProduktu;
    @FXML
    private Text tytulStrony;
    @FXML
    private ImageView zdjeciaObrazka;
    @FXML
    private Text dodanoDoKoszyka;
    @FXML
    public void initialize() {
        dodanoDoKoszyka.setVisible(false);
    }

    public void loadProductData(){
        this.tytulStrony.setText(this.produkt.getNazwa());
        this.opisProduktu.setText(this.produkt.getOpis());
        this.cenaProduktu.setText(String.valueOf(this.produkt.getCena()));
        if (this.zdjeciaObrazka != null) {
            try {
                String imageUrl = produkt.getUrlZdjecia();
                if (imageUrl != null && !imageUrl.trim().isEmpty()) {
                    // Załaduj zasób z katalogu resources
                    Image image = new Image(getClass().getResource(imageUrl).toExternalForm());
                    this.zdjeciaObrazka.setImage(image);
                } else {
                    System.err.println("URL obrazu jest pusty lub null");
                }
            } catch (Exception e) {
                System.err.println("Błąd przy ustawianiu obrazu: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.err.println("zdjeciaObrazka is null");
        }

    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    @FXML
    void dodajDoKoszyka(MouseEvent event) {
        if(SklepInternetowy.isLogin==true){
            SklepInternetowy.koszyk.add(this.produkt);
            dodanoDoKoszyka.setVisible(true);
//            HomePageController.wyswietlKoszyk();
        }else{
            SklepInternetowy.switchScene("/org/example/skelpinternetowy/Page/LogIn.fxml");
        }
    }

}
