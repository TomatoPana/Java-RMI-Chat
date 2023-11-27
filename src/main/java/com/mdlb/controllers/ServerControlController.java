package com.mdlb.controllers;

import com.mdlb.App;
import com.mdlb.server.ServerManagement;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ServerControlController {
    @FXML
    private ListView<String> logsList;

    @FXML
    private Label serverStatusLabel;

    private ServerManagement serverManagement;

    public void initialize() {
        App.getStage().setTitle("Chat Server Monitoring");
        App.getStage().setResizable(true);
        App.getStage().setMinWidth(400);
        App.getStage().setMinHeight(300);
        App.getStage().setWidth(800);
        App.getStage().setHeight(600);
        serverStatusLabel.setText("Server is running on IP " + App.getServerConfiguration().getFormattedIp());
        try {
            this.serverManagement = new ServerManagement()
                    .setIp(App.getServerConfiguration().getIp())
                    .setPort(App.getServerConfiguration().getPort())
                    .setLogger(logsList);

            this.serverManagement.start();
        } catch (Exception e) {
            showErrorDialog("Error", "Error while starting the server", e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Shows an error dialog with the given title, header and content.
     * 
     * @param title   The title of the dialog.
     * @param header  The header of the dialog.
     * @param content The content of the dialog.
     */
    private void showErrorDialog(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void clearConsole() {
        logsList.getItems().clear();
    }

    public void addLog(String log) {
        logsList.getItems().add(log);
    }

    @FXML
    public void shutdownServer() {
    }
}
