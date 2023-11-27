package com.mdlb.controllers;

import com.mdlb.App;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ServerControlController {
  @FXML
  private ListView<String> messageList;
  @FXML
  private TextField inputField;

  public void initialize() {
    App.getStage().setTitle("Chat Server Monitoring");
    App.getStage().setResizable(true);
    App.getStage().setMinWidth(400);
    App.getStage().setMinHeight(300);
  }

  @FXML
  public void sendMessage() {
    String message = inputField.getText();
    if (!message.trim().isEmpty()) {
      messageList.getItems().add(message);
      inputField.clear();
    }
  }
}
