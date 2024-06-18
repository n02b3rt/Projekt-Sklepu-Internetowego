package org.example.skelpinternetowy.Pages;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.skelpinternetowy.Hibernate.*;
import org.example.skelpinternetowy.SklepInternetowy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPanelController {

    @FXML
    private GridPane accionButtons;

    @FXML
    private Button cancleButton;

    @FXML
    private Button okButton;

    @FXML
    private AnchorPane ordersCustomer;

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
        showOrders();
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
        if(!SklepInternetowy.koszyk.isEmpty()) {
        SklepInternetowy.koszyk.clear();
        }
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

    private void showOrders() {
        ZamowienieDAO zamowienia = new ZamowienieDAO();
        List<Zamowienie> actualClientOrders = zamowienia.getAllClientOrder();
        float totalPrice = 0;

        if (!actualClientOrders.isEmpty()) {
            VBox orderBox = new VBox();

            Map<Integer, List<Produkt>> ordersGroupedByNumberOrders = new HashMap<>();
            for (Zamowienie order : actualClientOrders) {
                if (order.getIdZamowienia() != null) {
                    Produkt produkt = order.getProdukt(); // Pobieramy pojedynczy produkt
                    if (produkt != null) {
                        ordersGroupedByNumberOrders
                                .computeIfAbsent(order.getNrZamowienia(), k -> new ArrayList<>())
                                .add(produkt); // Dodajemy pojedynczy produkt do listy
                    } else {
                        System.out.println("Błąd: Produkt jest null.");
                    }
                } else {
                    System.out.println("Błąd: Identyfikator zamówienia jest null.");
                }
            }

            // Wyświetlanie zgrupowanych produktów
            for (Map.Entry<Integer, List<Produkt>> entry : ordersGroupedByNumberOrders.entrySet()) {
                VBox singleOrderBox = new VBox();
                VBox productBox = new VBox();
                HBox summaryBox = new HBox();

                System.out.println("Nr zamówienia: " + entry.getKey());

                for (Produkt produkt : entry.getValue()) {
                    System.out.println("Produkt ID: " + produkt.getIdProduktu());
                    HBox singleProductBox = new HBox();
                    singleProductBox = createProductBox(produkt);
                    productBox.getChildren().add(singleProductBox);
                    totalPrice += produkt.getCena();
                    System.out.println("totalPrice: " + totalPrice + " ProductID " + produkt.getIdProduktu() + " Cena: " + produkt.getCena());
                }

                Label orderNumberLabel = new Label("Zamówienie Numer: " + String.valueOf(entry.getKey()));
                orderNumberLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #ffffff; -fx-pref-width: 290px;");
                Label totalPriceLabel = new Label("Łącznie: " + String.valueOf(totalPrice) + " zl");
                totalPriceLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #ffffff; -fx-pref-width: 290px; -fx-alignment: CENTER-RIGHT");

                summaryBox.getChildren().addAll(orderNumberLabel,totalPriceLabel);
                summaryBox.setStyle("-fx-padding: 15px;");

                singleOrderBox.getChildren().addAll(productBox,summaryBox);
                singleOrderBox.setStyle("-fx-margin: 25px; -fx-background-color: ff9f7b;");
                orderBox.getChildren().addAll(singleOrderBox);
                totalPrice = 0;
            }

            orderBox.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.5, 0, 0);");
            ordersCustomer.getChildren().addAll(orderBox);
//            ordersCustomer.setStyle("-fx-padding: 10px;");

        } else {
            Text text = new Text();
            text.setText("Nie złożyłeś żadnych zamówień");
            text.setStyle("-fx-font-size: 20px");
            System.out.println("Nie ma zamówień");
        }
    }



//    private Integer checkingNumberOfOrders(List<Zamowienie> zamowienia){
//        Integer currentOrderNumber = zamowienia.getLast().getNrZamowienia();
//        Integer NumberOfOrders = 1;
//
//        for (Zamowienie zamowienie : zamowienia) {
//            if(currentOrderNumber != zamowienie.getIdZamowienia()){
//                NumberOfOrders++;
//                currentOrderNumber = zamowienie.getIdZamowienia();
//            }
//        }
//        return NumberOfOrders;
//    }

    private HBox createProductBox(Produkt product) {
        HBox productBox = new HBox();
//        productBox.setSpacing(30);
        productBox.setAlignment(Pos.CENTER);

        // Utwórz pusty ImageView
        ImageView productPhoto = new ImageView();

        // Ustaw obraz dynamicznie
        String imagePath = product.getUrlZdjecia();
        System.out.println(imagePath);
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            productPhoto.setImage(image);
        } catch (Exception e) {
            // Jeśli obraz nie zostanie znaleziony, użyj domyślnego obrazu
            Image image = new Image(getClass().getResourceAsStream("/images/products/defaultPicture.png"));
            productPhoto.setImage(image);
        }
        productPhoto.setFitWidth(75);
        productPhoto.setPreserveRatio(true);

        Label idLabel = new Label("id produktu: " + String.valueOf(product.getIdProduktu()));
        idLabel.setStyle("-fx-font-size: 18px; -fx-pref-width: 150px;");
        Label nameLabel = new Label(product.getNazwa());

        nameLabel.setStyle("-fx-font-size: 18px; -fx-pref-width: 300px;");
        nameLabel.setAlignment(Pos.CENTER);

        Label priceLabel = new Label(String.valueOf(product.getCena()) + " zł");
        priceLabel.setStyle("-fx-font-size: 18px; -fx-pref-width: 135px;");

        HBox dataBox = new HBox();
        dataBox.setAlignment(Pos.CENTER);
        dataBox.getChildren().addAll(idLabel,nameLabel,priceLabel);
        dataBox.setStyle("-fx-pref-width: 535px; -fx-padding: 10px;");

        productBox.getChildren().addAll(productPhoto, dataBox);
        productBox.setStyle("-fx-pref-width: 600px; -fx-background-color: #ffffff;");

        return productBox;
    }

}
