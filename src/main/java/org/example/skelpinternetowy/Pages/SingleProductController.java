package org.example.skelpinternetowy.Pages;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.skelpinternetowy.Hibernate.Produkt;

public class SingleProductController {
    private Produkt produkt;
    @FXML
    public void initialize() {

    }

//    public void setProduct(Produkt produkt) {
//        this.produkt = produkt;
//        productNameLabel.setText(produkt.getNazwa());
//        productDescriptionLabel.setText(produkt.getOpis());
//        productPriceLabel.setText("Cena: " + produkt.getCena());
//        String imagePath = produkt.getUrlZdjecia();
//        try {
//            Image image = new Image(getClass().getResourceAsStream(imagePath));
//            productImageView.setImage(image);
//        } catch (Exception e) {
//            Image image = new Image(getClass().getResourceAsStream("/images/products/defaultPicture.png"));
//            productImageView.setImage(image);
//        }
//    }
}
