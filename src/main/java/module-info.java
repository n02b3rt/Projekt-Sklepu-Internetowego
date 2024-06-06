module org.example.skelpinternetowy {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.skelpinternetowy to javafx.fxml;
    exports org.example.skelpinternetowy;
}