package org.example.skelpinternetowy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SklepInternetowy extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        String cssFile = getClass().getResource("/org/example/skelpinternetowy/styles/styleGlowne.css").toExternalForm();
        FXMLLoader fxmlLoader = new FXMLLoader(SklepInternetowy.class.getResource("homePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(cssFile);
        stage.setTitle("Sklep Internetowy");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}