module org.graded {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;
    requires atlantafx.base;
    opens org.graded to javafx.fxml;
    exports org.graded;
}