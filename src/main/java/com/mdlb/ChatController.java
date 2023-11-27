package com.mdlb;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ChatController {
    @FXML
    private ListView<String> messageList;
    @FXML
    private TextField inputField;

    @FXML
    public void sendMessage() {
        String message = inputField.getText();
        if (!message.trim().isEmpty()) {
            messageList.getItems().add(message);
            inputField.clear();
        }
    }
}
