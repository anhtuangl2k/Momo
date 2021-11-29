module com.momo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.momo to javafx.fxml;
    exports com.momo;
}
