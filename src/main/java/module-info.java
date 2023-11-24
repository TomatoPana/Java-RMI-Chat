module com.mdlb {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.rmi;

    opens com.mdlb to javafx.fxml;
    opens com.mdlb.controllers to javafx.fxml;

    exports com.mdlb;
    exports com.mdlb.DTOs;
    exports com.mdlb.interfaces;
    exports com.mdlb.controllers;
}