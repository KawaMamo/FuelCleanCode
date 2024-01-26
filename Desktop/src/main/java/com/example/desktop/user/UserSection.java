package com.example.desktop.user;

import com.example.desktop.HelloApplication;
import com.example.model.client.Client;
import com.example.model.modal.Modal;
import com.example.model.user.LogInData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserSection {

    @FXML
    private Label usernameLbl;

    @FXML
    private void initialize(){
        usernameLbl.setText(LogInData.loggedInUser.getEmail());
    }

    @FXML
    private void logout(){
        Client.setAuthorization(null);
        HelloApplication.setScene("hello-view");
    }

    @FXML
    private void passwordReset(){
        Modal.start(this.getClass(), "resetPassword.fxml");
    }
}
