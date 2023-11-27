package com.mdlb.controllers;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

import com.mdlb.App;
import com.mdlb.DTOs.ServerConfiguration;

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

    /**
     * Initializes the controller class and handles the initial setup.
     */
    public void initialize() {
        App.getStage().setTitle("Configuring the server");
        ObservableList<String> ipList = FXCollections.observableArrayList(getLocalAddresses());
        ipComboBox.setItems(ipList);
        ipComboBox.getSelectionModel().selectFirst();
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

    /**
     * Gets the local addresses of the host.
     * 
     * @return an ArrayList of Strings containing the local addresses of the host.
     */
    private ArrayList<String> getLocalAddresses() {
        ArrayList<String> foundAddresses = new ArrayList<String>();
        try {
            // Get all the interfaces of the host
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                // Get the next interface, or first if its the first iteration
                NetworkInterface networkInterface = interfaces.nextElement();
                if (!networkInterface.isUp()) // Skip if the interface is down
                    continue;
                // For each interface, get all the addresses
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    // Add only IPv4 addresses
                    if (address.getAddress().length == 4) {
                        foundAddresses.add(address.getHostAddress());
                    }

                    // if (address.isLoopbackAddress()) // Skip if the address is a loopback address
                    // continue;
                }
            }
        } catch (SocketException e) {
            showErrorDialog("Error", "Error getting network interfaces",
                    "Either the host is not connected to a network or the network is down.");
            e.printStackTrace();
        }
        return foundAddresses;
    }

    /**
     * Handles the validation to start of the server. If everything is correct, the
     * server is started.
     * 
     * @throws IOException
     */
    @FXML
    private void startServer() throws IOException {
        String ip = ipComboBox.getValue();
        String port = portTextField.getText();

        if (port.trim().isEmpty()) {
            showErrorDialog("Error", "Invalid Input", "Port cannot be empty");
        } else if (!port.matches("[0-9]+")) {
            showErrorDialog("Error", "Invalid Input", "Port must be a number");
        } else {
            App.setServerConfiguration(new ServerConfiguration(ip, port));
            App.setRoot("server/server_control");
        }
    }
}
