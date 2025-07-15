module org.graded {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.xerial.sqlitejdbc;
    requires java.sql;
    requires org.slf4j.nop;
    requires java.desktop;
    requires atlantafx.base;
    opens org.graded to javafx.fxml, java.sql, org.xerial.sqlitejdbc;
    exports org.graded;
}