module com.example.passwordbank {
    
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive java.desktop;
    requires transitive animatefx;
    requires java.xml;
    requires java.compiler;

    opens com.example.passwordbank to javafx.fxml;
    opens com.example.passwordbank.controllers to javafx.fxml;
    opens com.example.passwordbank.utilities to javafx.fxml;
    opens com.example.passwordbank.model to javafx.fxml;

    exports com.example.passwordbank to javafx.graphics;
    exports com.example.passwordbank.controllers to javafx.graphics;
    exports com.example.passwordbank.utilities to javafx.graphics;
    exports com.example.passwordbank.model to javafx.graphics;
}