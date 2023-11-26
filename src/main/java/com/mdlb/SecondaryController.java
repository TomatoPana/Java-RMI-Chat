package com.mdlb;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SecondaryController {

    @FXML
    private Button secondaryButton;

    @FXML
    private TextField textField;

    public void initialize() {
        secondaryButton.setText("Text");
        textField.setPromptText("Prompt?");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}