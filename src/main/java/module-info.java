module com.mdlb {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.rmi;

    opens com.mdlb to javafx.fxml;

    exports com.mdlb;
    exports com.mdlb.interfaces;
}
