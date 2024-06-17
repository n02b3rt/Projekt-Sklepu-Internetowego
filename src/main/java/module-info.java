module org.example.skelpinternetowy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;


    opens org.example.skelpinternetowy to org.hibernate.orm.core, javafx.fxml;
    exports org.example.skelpinternetowy;
    exports org.example.skelpinternetowy.Pages;
    opens org.example.skelpinternetowy.Pages to javafx.fxml;
    exports org.example.skelpinternetowy.UI;
    opens org.example.skelpinternetowy.UI to javafx.fxml;
    exports org.example.skelpinternetowy.Hibernate;
    opens org.example.skelpinternetowy.Hibernate to javafx.fxml, org.hibernate.orm.core;
}