package com.mdlb;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    public PrimaryController() {
        System.out.println("PrimaryController");
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
