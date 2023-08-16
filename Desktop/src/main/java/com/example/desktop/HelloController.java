package com.example.desktop;

import com.example.model.Client;
import com.example.model.JWTToken;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.http.HttpResponse;

public class HelloController {
    @FXML
    private PasswordField passwordTF;

    @FXML
    private Button submitBtn;

    @FXML
    private TextField usernameTF;

    private final Client client = Client.getInstance();


    @FXML
    void submit(ActionEvent event) {
        String payload = "{\"email\":\""+usernameTF.getText()+"\",\""+passwordTF.getText()+"\":\"password\"}";
        HttpResponse<String> post = client.post("api/v1/auth/authenticate", payload);
        final JWTToken jwtToken = new Gson().fromJson(post.body(), JWTToken.class);
        System.out.println(jwtToken.getToken());
    }

}