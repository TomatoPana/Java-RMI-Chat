package com.mdlb.controllers;

import java.io.IOException;

import com.mdlb.App;

import javafx.fxml.FXML;

public class StartClientController {

    public StartClientController() {
        System.out.println("PrimaryController");
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
