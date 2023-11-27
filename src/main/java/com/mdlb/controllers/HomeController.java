package com.mdlb.controllers;

import javafx.event.ActionEvent;
import java.io.IOException;

import com.mdlb.App;

import javafx.fxml.FXML;

public class HomeController {

    @FXML
    private void loadServerApplication(ActionEvent event) throws IOException {
        System.out.println("Load Server View");
        App.setRoot("server/start_server");
    }

    @FXML
    private void loadClientApplication(ActionEvent event) throws IOException {
        System.out.println("Load Client View");
        App.setRoot("client/start_client");
    }

}
