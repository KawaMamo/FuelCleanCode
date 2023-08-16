package com.example.desktop;

import com.example.model.Client;
import com.example.model.JWTToken;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.http.HttpResponse;
import java.util.HashMap;

public class HelloController {
    @FXML
    private PasswordField passwordTF;

    @FXML
    private Button submitBtn;

    @FXML
    private TextField usernameTF;

    private final Client client = Client.getInstance();


    @FXML
    void submit() {
        submitBtn.setDisable(true);
        final String json = getString();
        HttpResponse<String> post = client.post("api/v1/auth/authenticate", json);
        final JWTToken jwtToken = new Gson().fromJson(post.body(), JWTToken.class);
        client.setAuthorization("Bearer "+jwtToken.getToken());
        submitBtn.setDisable(false);
        System.out.println(client.getAuthorization());
    }

    private String getString() {
        final HashMap<String, String> payloadObj = new HashMap<>();
        payloadObj.put("email", usernameTF.getText());
        payloadObj.put("password", passwordTF.getText());
        return new Gson().toJson(payloadObj);
    }

}