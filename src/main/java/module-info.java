module com.javafx.pos {
    requires java.persistence;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires slf4j.api;
    requires java.sql;

    opens com.javafx.pos.controller to javafx.fxml;
    opens com.javafx.pos.pojo to org.hibernate.orm.core, javafx.base;
    exports com.javafx.pos;
}