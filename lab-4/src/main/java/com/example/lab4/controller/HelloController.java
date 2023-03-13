package com.example.lab4.controller;

import com.example.lab4.domain.CurrentUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {
    CurrentUser currentUser = CurrentUser.getInstance();
    @FXML
    private Button loginButton;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;

    public void handleLogin() throws Exception {
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();

        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/lab4/signedin-view.fxml"));
        Parent root = loader.load();

        SignedInController signedInController = loader.getController();

        if (signedInController.service.getUser(firstName, lastName) != null) {
            Stage window = (Stage) loginButton.getScene().getWindow();
            window.setScene(new Scene(root,720, 480));
        }
        else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid");
            errorAlert.setContentText("Empty field or user does not exist");
            errorAlert.showAndWait();
        }
    }
}
