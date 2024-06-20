package org.example.skelpinternetowy.Pages;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
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

/**
 * Kontroler dla panelu użytkownika.
 * Odpowiada za zarządzanie danymi użytkownika oraz wyświetlanie zamówień.
 */
public class UserPanelController {

    @FXML
    private GridPane accionButtons;

    @FXML
    private Button cancleButton;

    @FXML
    private Button logout;

    @FXML
    private Button okButton;

    @FXML
    private Button DeleteUserBtn;

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
    private Text tytulstrony;

    @FXML
    private TextField surname;

    /**
     * Inicjalizuje kontroler i ładuje dane użytkownika oraz zamówienia.
     * Ustawia pola tekstowe danymi aktualnego klienta oraz wywołuje metodę {@link #showOrders()},
     * która wyświetla zamówienia klienta.
     */
    @FXML
    public void initialize() {
        // Ustawia pola tekstowe danymi aktualnego klienta
        loadKlientData();
        // Wyświetla zamówienia klienta
        showOrders();
    }

    /**
     * Anuluje zmiany danych użytkownika.
     * Ukrywa przyciski akcji, pokazuje przycisk zmiany i ustawia pola tekstowe jako nieedytowalne
     * oraz zmienia ich kolor tła.
     *
     * @param event zdarzenie kliknięcia myszą
     */
    @FXML
    void cancleChanges(MouseEvent event) {
        // Ukrywa przyciski akcji i pokazuje przycisk zmiany
        accionButtons.setVisible(false);
        changeButton.setVisible(true);
        // Ustawia pola na nieedytowalne i zmienia kolor tła
        showFields(false, SklepInternetowy.mainColor);
        loadKlientData();

    }

    /**
     * Usuwa konto użytkownika po potwierdzeniu przez użytkownika.
     *
     * @param event zdarzenie kliknięcia myszą
     */
    @FXML
    void delauser(MouseEvent event){
        showAlert2("Czy na pewno chcesz usunąć konto?","Proces ten jest nieodwracalny.");
    }

    /**
     * Zmienia dane użytkownika.
     * Pobiera dane z pól tekstowych, sprawdza ich poprawność oraz aktualizuje dane klienta w bazie.
     * Wyświetla odpowiednie alerty w przypadku błędów.
     *
     * @param event zdarzenie kliknięcia myszą
     */
    @FXML
    void changeData(MouseEvent event) {
        // Pobiera dane z pól tekstowych
        String changedname = name.getText();
        String changedsurname = surname.getText();
        String changedaddress = address.getText();
        String changedemail = email.getText();
        String changedlogin = nick.getText();
        String changedpassword = password.getText();

        // Sprawdza, czy login został zmieniony
        boolean isLoginChanged = !changedlogin.equals(SklepInternetowy.actualKlient.getNazwa());
        KlientDAO klientDAO = new KlientDAO();
        Klient myKlient = isLoginChanged ? klientDAO.getByNazwa(changedlogin) : null;

        // Sprawdza, czy nowy login jest już zajęty
        if (isLoginChanged && myKlient != null) {
            // Wyświetla alert, jeśli login jest zajęty
            showAlert("Login zajęty!", "Wybrany login jest już zajęty, wybierz inny.");
        } else if (changedname.isEmpty() || changedsurname.isEmpty() || changedaddress.isEmpty() || changedemail.isEmpty() || changedlogin.isEmpty() || changedpassword.isEmpty()) {
            // Wyświetla alert, jeśli jakieś pole jest puste
            showAlert("Nie można edytować profilu!", "Wszystkie pola informacji muszą być wypełnione.");
        } else if (changedpassword.length() < 6) {
            // Wyświetla alert, jeśli hasło jest zbyt krótkie
            showAlert("Nie można edytować profilu!", "Hasło nie powinno mieć mniej niż 6 znaków.");
        } else {
            // Aktualizuje dane klienta
            Klient klient = SklepInternetowy.actualKlient;
            klient.setImie(changedname);
            klient.setNazwisko(changedsurname);
            klient.setAdres(changedaddress);
            klient.setEmail(changedemail);
            klient.setNazwa(changedlogin);
            klient.setHaslo(changedpassword);
            klientDAO.editKlient(klient);
            // Wyświetla alert z potwierdzeniem
            showAlert("Gratulacje!", "Dane klienta zostały zaktualizowane.");
            // Ukrywa przyciski akcji i pokazuje przycisk zmiany
            accionButtons.setVisible(false);
            changeButton.setVisible(true);
            // Ustawia pola na nieedytowalne i zmienia kolor tła
            showFields(false, SklepInternetowy.mainColor);
        }
    }

    /**
     * Przełącza pola tekstowe na tryb edycji.
     * Pokazuje przyciski akcji, ukrywa przycisk zmiany oraz ustawia pola tekstowe jako edytowalne
     * i zmienia ich kolor tła.
     *
     * @param event zdarzenie kliknięcia myszą
     */
    @FXML
    void modifyData(MouseEvent event) {
        // Pokazuje przyciski akcji i ukrywa przycisk zmiany
        accionButtons.setVisible(true);
        changeButton.setVisible(false);
        // Ustawia pola na edytowalne i zmienia kolor tła
        showFields(true, "#ffffff");
    }

    void loadKlientData(){
        address.setText(SklepInternetowy.actualKlient.getAdres());
        email.setText(SklepInternetowy.actualKlient.getEmail());
        name.setText(SklepInternetowy.actualKlient.getImie());
        nick.setText(SklepInternetowy.actualKlient.getNazwa());
        password.setText(SklepInternetowy.actualKlient.getHaslo());
        surname.setText(SklepInternetowy.actualKlient.getNazwisko());
    }
    /**
     * Wylogowuje użytkownika i przełącza na stronę główną.
     * Czyści koszyk, ustawia status zalogowania na false oraz przełącza scenę na stronę główną.
     *
     * @param event zdarzenie kliknięcia myszą
     */
    @FXML
    void logout(MouseEvent event) {
        // Czyści koszyk, ustawia status zalogowania na false
        accionButtons.setVisible(true);
        changeButton.setVisible(false);
        if (!SklepInternetowy.koszyk.isEmpty()) {
            SklepInternetowy.koszyk.clear();
        }
        SklepInternetowy.isLogin = false;
        SklepInternetowy.actualKlient = new Klient();
        // Przełącza scenę na stronę główną
        SklepInternetowy.switchScene("/homePage.fxml");
    }

    /**
     * Ustawia pola tekstowe jako edytowalne lub nieedytowalne oraz zmienia ich kolor tła.
     * Ustawia możliwość edycji i kolor tła dla każdego pola tekstowego.
     *
     * @param stan   czy pola powinny być edytowalne
     * @param colour kolor tła pól
     */
    private void showFields(boolean stan, String colour) {
        String backgroundColour = "-fx-background-color: " + colour + ";";

        // Ustawia możliwość edycji i kolor tła dla każdego pola tekstowego i kryje niepotrzebne guziki
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
        DeleteUserBtn.setVisible(!stan);
        logout.setVisible(!stan);
    }

    /**
     * Wyświetla alert z podanym tytułem i treścią.
     * Tworzy i wyświetla okno alertu informacyjnego z podanym tytułem i treścią.
     *
     * @param messageheader tytuł alertu
     * @param message2content treść alertu
     */
    private void showAlert(String messageheader, String message2content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText(messageheader);
        alert.setContentText(message2content);
        alert.showAndWait();
    }

    /**
     * Wyświetla alert potwierdzający usunięcie konta.
     * Tworzy i wyświetla okno alertu potwierdzenia usunięcia konta. Jeśli użytkownik potwierdzi,
     * konto zostanie usunięte, a użytkownik zostanie wylogowany i przekierowany na stronę główną.
     *
     * @param messageheader tytuł alertu
     * @param message2content treść alertu
     */
    private void showAlert2(String messageheader, String message2content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText(messageheader);
        alert.setContentText(message2content);

        ButtonType buttonTypeYes = new ButtonType("Tak");
        ButtonType buttonTypeNo = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeNo, buttonTypeYes);

        alert.showAndWait().ifPresent(type -> {
            if (type == buttonTypeYes) {
                // Usunięcie zamówień klienta
                ZamowienieDAO zamowienieDAO = new ZamowienieDAO();
                List<Zamowienie> clientOrders = zamowienieDAO.getAllClientOrder();
                for (Zamowienie zamowienie : clientOrders) {
                    zamowienieDAO.deleteZamowienie(zamowienie);
                }

                // Usunięcie konta
                KlientDAO klientDAO = new KlientDAO();
                klientDAO.deleteKlient(SklepInternetowy.actualKlient);
                SklepInternetowy.actualKlient = null;
                SklepInternetowy.isLogin = false;
                SklepInternetowy.koszyk.clear();
                SklepInternetowy.switchScene("/homePage.fxml");
            }
        });
    }



    /**
     * Wyświetla zamówienia użytkownika.
     * Pobiera zamówienia użytkownika z bazy danych za pomocą klasy `ZamowienieDAO`
     * i wyświetla je w widoku użytkownika. Zamówienia są grupowane według numeru zamówienia,
     * a następnie tworzony jest kontener zawierający produkty dla każdego zamówienia
     * oraz podsumowanie zamówienia z całkowitą ceną.
     *
     * W przypadku braku zamówień, użytkownik zostaje poinformowany,
     * że nie złożono żadnych zamówień.
     */
    private void showOrders() {
        ZamowienieDAO zamowienia = new ZamowienieDAO();
        List<Zamowienie> actualClientOrders = zamowienia.getAllClientOrder();
        float totalPrice = 0;

        // Sprawdza, czy lista zamówień nie jest pusta
        if (!actualClientOrders.isEmpty()) {
            VBox orderBox = new VBox(); // Główny kontener dla wszystkich zamówień

            // Grupuje zamówienia według numeru zamówienia
            Map<Integer, List<Produkt>> ordersGroupedByNumberOrders = new HashMap<>();
            for (Zamowienie order : actualClientOrders) {
                // Sprawdza, czy zamówienie ma poprawny identyfikator
                if (order.getIdZamowienia() != null) {
                    Produkt produkt = order.getProdukt(); // Pobiera pojedynczy produkt
                    // Sprawdza, czy produkt nie jest null
                    if (produkt != null) {
                        ordersGroupedByNumberOrders
                                .computeIfAbsent(order.getNrZamowienia(), k -> new ArrayList<>())
                                .add(produkt); // Dodaje produkt do listy produktów zgrupowanych według numeru zamówienia
                    } else {
                        System.out.println("Błąd: Produkt jest null.");
                    }
                } else {
                    System.out.println("Błąd: Identyfikator zamówienia jest null.");
                }
            }

            // Iteruje przez zgrupowane zamówienia i wyświetla je
            for (Map.Entry<Integer, List<Produkt>> entry : ordersGroupedByNumberOrders.entrySet()) {
                VBox singleOrderBox = new VBox(); // Kontener dla pojedynczego zamówienia
                VBox productBox = new VBox(); // Kontener dla produktów w zamówieniu
                HBox summaryBox = new HBox(); // Kontener dla podsumowania zamówienia

                System.out.println("Nr zamówienia: " + entry.getKey());

                // Iteruje przez produkty w danym zamówieniu i dodaje je do kontenera
                for (Produkt produkt : entry.getValue()) {
                    System.out.println("Produkt ID: " + produkt.getIdProduktu());
                    HBox singleProductBox = createProductBox(produkt); // Tworzy HBox dla pojedynczego produktu
                    productBox.getChildren().add(singleProductBox); // Dodaje HBox do kontenera produktów
                    totalPrice += produkt.getCena(); // Dodaje cenę produktu do całkowitej ceny zamówienia
                    System.out.println("totalPrice: " + totalPrice + " ProductID " + produkt.getIdProduktu() + " Cena: " + produkt.getCena());
                }

                // Tworzy etykiety dla numeru zamówienia i całkowitej ceny
                Label orderNumberLabel = new Label("Zamówienie Numer: " + String.valueOf(entry.getKey()));
                orderNumberLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #ffffff; -fx-pref-width: 290px;");
                Label totalPriceLabel = new Label("Łącznie: " + String.valueOf(totalPrice) + " zł");
                totalPriceLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #ffffff; -fx-pref-width: 290px; -fx-alignment: CENTER-RIGHT");

                // Dodaje etykiety do kontenera podsumowania
                summaryBox.getChildren().addAll(orderNumberLabel, totalPriceLabel);
                summaryBox.setStyle("-fx-padding: 15px;");

                // Dodaje kontener produktów i podsumowanie do głównego kontenera zamówienia
                singleOrderBox.getChildren().addAll(productBox, summaryBox);
                singleOrderBox.setStyle("-fx-margin: 25px; -fx-background-color: ff9f7b;");
                orderBox.getChildren().addAll(singleOrderBox); // Dodaje zamówienie do głównego kontenera zamówień
                totalPrice = 0; // Resetuje całkowitą cenę dla następnego zamówienia
            }

            // Ustawia styl dla głównego kontenera zamówień i dodaje go do głównego widoku
            orderBox.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.5, 0, 0);");
            ordersCustomer.getChildren().addAll(orderBox);

        } else {
            // Ustawia tekst tytułu strony, jeśli nie ma zamówień
            tytulstrony.setText("Nie złożono żadnych zamówień");
            System.out.println("Nie ma zamówień");
        }
    }

    /**
     * Tworzy HBox z informacjami o produkcie.
     * Tworzy kontener HBox zawierający informacje o produkcie, takie jak zdjęcie, id, nazwa oraz cena.
     * Jeśli obraz produktu nie zostanie znaleziony, używa domyślnego obrazu.
     *
     * @param product obiekt Produkt, który ma być wyświetlony
     * @return HBox zawierający informacje o produkcie
     */
    private HBox createProductBox(Produkt product) {
        HBox productBox = new HBox(); // Kontener dla pojedynczego produktu
        productBox.setAlignment(Pos.CENTER); // Ustawia wyrównanie do środka

        // Tworzy pusty ImageView dla zdjęcia produktu
        ImageView productPhoto = new ImageView();

        // Ustawia obraz dynamicznie
        String imagePath = product.getUrlZdjecia();
        System.out.println(imagePath);
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            productPhoto.setImage(image); // Ustawia obraz w ImageView
        } catch (Exception e) {
            // Jeśli obraz nie zostanie znaleziony, użyj domyślnego obrazu
            Image image = new Image(getClass().getResourceAsStream("/images/products/defaultPicture.png"));
            productPhoto.setImage(image);
        }
        productPhoto.setFitWidth(75); // Ustawia szerokość obrazu
        productPhoto.setPreserveRatio(true); // Zachowuje proporcje obrazu

        // Tworzy i stylizuje etykiety z informacjami o produkcie
        Label idLabel = new Label("id produktu: " + String.valueOf(product.getIdProduktu()));
        idLabel.setStyle("-fx-font-size: 18px; -fx-pref-width: 150px;");
        Label nameLabel = new Label(product.getNazwa());
        nameLabel.setStyle("-fx-font-size: 18px; -fx-pref-width: 300px;");
        nameLabel.setAlignment(Pos.CENTER);

        Label priceLabel = new Label(String.valueOf(product.getCena()) + " zł");
        priceLabel.setStyle("-fx-font-size: 18px; -fx-pref-width: 135px;");

        // Tworzy kontener dla danych produktu
        HBox dataBox = new HBox();
        dataBox.setAlignment(Pos.CENTER); // Ustawia wyrównanie do środka
        dataBox.getChildren().addAll(idLabel, nameLabel, priceLabel); // Dodaje etykiety do kontenera
        dataBox.setStyle("-fx-pref-width: 535px; -fx-padding: 10px;");

        // Dodaje obraz i dane produktu do głównego kontenera produktu
        productBox.getChildren().addAll(productPhoto, dataBox);
        productBox.setStyle("-fx-pref-width: 600px; -fx-background-color: #ffffff;");

        return productBox; // Zwraca gotowy HBox z informacjami o produkcie
    }
}
