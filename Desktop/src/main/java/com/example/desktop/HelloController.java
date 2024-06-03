package com.example.desktop;

import com.example.model.client.Client;
import com.example.model.user.JWTToken;
import com.example.model.user.LogInData;
import com.example.model.user.TokenData;
import com.example.model.user.User;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.HashMap;

import static com.example.model.properties.AppProperty.getProperties;

public class HelloController {
    @FXML
    private PasswordField passwordTF;
    @FXML
    private Button submitBtn;
    @FXML
    private TextField usernameTF;
    private final Client client = Client.getInstance(getProperties().getProperty("identity.provider.host")+":"+ getProperties().getProperty("identity.provider.port") +"/");

    @FXML
    void initialize(){
        submitBtn.setDefaultButton(true);
    }
    @FXML
    void submit() {
        final String payload = formPayload();
        HttpResponse<String> post = client.parallelPost("api/v1/auth/authenticate", payload);
        final JWTToken jwtToken = new Gson().fromJson(post.body(), JWTToken.class);
        Client.setAuthorization("Bearer "+jwtToken.getToken());
        setUserDetails(jwtToken);
        if(LogInData.loggedInUser.getRole().equals("SUPER_ADMIN"))
            HelloApplication.setScene("adminArea");
        else if(LogInData.loggedInUser.getRole().equals("TRANS_OFFICE"))
            HelloApplication.setScene("officeArea");
        else if(LogInData.loggedInUser.getRole().equals("ACCOUNTANT"))
            HelloApplication.setScene("accountantArea");
        else if(LogInData.loggedInUser.getRole().equals("SCANNER"))
            HelloApplication.setScene("scannerArea");

    }
    private void setUserDetails(JWTToken jwtToken) {
        final String decodedToken = new String(Base64.getUrlDecoder().decode(jwtToken.getToken().split("\\.")[1]));
        final TokenData tokenData = new Gson().fromJson(decodedToken, TokenData.class);
        LogInData.setLoggedInUser(new User(tokenData.UserId,
                tokenData.sub,
                passwordTF.getText(),
                tokenData.Roles.get(0).toString(),
                1));
    }
    private String formPayload() {
        final HashMap<String, String> payloadObj = new HashMap<>();
        payloadObj.put("email", usernameTF.getText());
        payloadObj.put("password", passwordTF.getText());
        return new Gson().toJson(payloadObj);
    }

}