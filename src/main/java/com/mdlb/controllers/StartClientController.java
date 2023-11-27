package com.mdlb.controllers;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.rmi.Naming;
import com.mdlb.interfaces.ChatManagementInterface;

public class StartClientController {

    @FXML
    private TextField ipTextField;

    @FXML
    private TextField portTextField;

    @FXML
    private Button connectButton;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private void connectToServer() throws Exception {
        connectButton.setText("Connecting...");
        connectButton.setDisable(true);

        Task<Void> connectTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                String address = ipTextField.getText();
                String port = portTextField.getText();

                if (address.isEmpty() || port.isEmpty()) {
                    showErrorDialog("Error", "Error connecting to server", "Please fill all the fields");
                    connectButton.setText("Connect to the server");
                    connectButton.setDisable(false);
                    return null;
                }

                // Validate if address is a valid IPv4 Address
                if (!validateIPAdress(address)) {
                    showErrorDialog("Error", "Error connecting to server", "Invalid IP Address");
                    connectButton.setText("Connect to the server");
                    connectButton.setDisable(false);
                    return null;
                }

                // Validate if port is a valid number
                if (!validatePort(port)) {
                    showErrorDialog("Error", "Error connecting to server", "Invalid port");
                    connectButton.setText("Connect to the server");
                    connectButton.setDisable(false);
                    return null;
                }

                // Validate if the IP is reachable
                if (!isIPReachableAndPortOpen(address, Integer.parseInt(port))) {
                    showErrorDialog("Error", "Error connecting to server",
                            "The IP address is not reachable or the port is not open");
                    connectButton.setText("Connect to the server");
                    connectButton.setDisable(false);
                    return null;
                }

                String name = "//" + ipTextField.getText() + ":" + portTextField.getText() + "/ChatManagement";
                try {
                    ChatManagementInterface RMI = (ChatManagementInterface) Naming.lookup(name);
                } catch (Exception e) {
                    showErrorDialog("Error",
                            "Error connecting to " + ipTextField.getText() + ":" + portTextField.getText(),
                            e.getMessage());
                    e.printStackTrace();
                    connectButton.setText("Connect to the server");
                    connectButton.setDisable(false);
                }
                return null;
            }
        };

        connectTask.setOnSucceeded(e -> {
            connectButton.setText("Connected to the server");
            connectButton.setDisable(false);
        });

        connectTask.setOnFailed(e -> {
            connectButton.setText("Connect to the server");
            connectButton.setDisable(false);
        });

        new Thread(connectTask).start();
    }

    private boolean validateIPAdress(String address) {
        String[] addressParts = address.split("\\.");
        if (addressParts.length != 4) {
            return false;
        }
        for (String part : addressParts) {
            try {
                int number = Integer.parseInt(part);
                if (number < 0 || number > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private boolean validatePort(String port) {
        try {
            int portNumber = Integer.parseInt(port);
            if (portNumber < 0 || portNumber > 65535) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean isIPReachableAndPortOpen(String ip, int port) {
        try {
            InetAddress address = InetAddress.getByName(ip);
            if (address.isReachable(5000)) {
                try (Socket socket = new Socket(address, port)) {
                    return true;
                } catch (IOException ex) {
                    // The port is not open
                    return false;
                }
            } else {
                // The IP is not reachable
                return false;
            }
        } catch (IOException ex) {
            // The IP address is not valid
            return false;
        }
    }

    @FXML
    private void loginToServer() throws IOException {

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
}
