module com.example.passwordbank {
    
    requires transitive javafx.base;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.desktop;
    requires animatefx;

    opens com.example.passwordbank to javafx.fxml;
    opens com.example.passwordbank.controllers to javafx.fxml;

    exports com.example.passwordbank.controllers to javafx.graphics;
    exports com.example.passwordbank.utilities to javafx.graphics;
    exports com.example.passwordbank.model to javafx.graphics;
    exports com.example.passwordbank to javafx.graphics;
}