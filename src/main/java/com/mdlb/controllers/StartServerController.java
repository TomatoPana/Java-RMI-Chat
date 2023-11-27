package com.mdlb.controllers;

import java.io.IOException;

import com.mdlb.App;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

public class StartServerController {
    @FXML
    private ComboBox<String> ipComboBox;

    @FXML
    private TextField portTextField;

    @FXML
    private Button startServerButton;

    public void initialize() {
        ObservableList<String> ipList = FXCollections.observableArrayList("127.0.0.1");
        ipComboBox.setItems(ipList);
        ipComboBox.getSelectionModel().selectFirst();
    }

    private void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void startServer() throws IOException {
        String ip = ipComboBox.getValue();
        String port = portTextField.getText();

        if (port.trim().isEmpty()) {
            showErrorDialog("Error", "Invalid Input", "Port cannot be empty");
        } else {
            System.out.println("ip: " + ip + ", port: " + port);
            App.setRoot("chat");
        }
    }
}
