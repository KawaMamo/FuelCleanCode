package com.example.desktop.user;

import com.example.model.user.LogInData;
import com.example.model.user.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import org.controlsfx.control.Notifications;

public class ResetPassword {

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField repeatPassword;

    UserService userService = UserService.getInstance();

    @FXML
    void submit(){
        String message = "";
        if(newPassword.getText().length() >0 && oldPassword.getText().length() >0 && repeatPassword.getText().length() >0){
            if(LogInData.loggedInUser.getPassword().equals(oldPassword.getText())){
                if(newPassword.getText().equals(repeatPassword.getText())){
                    LogInData.loggedInUser.setPassword(newPassword.getText());
                    userService.update(LogInData.loggedInUser, LogInData.loggedInUser.getId(), oldPassword.getText());
                    message ="success";
                }else message = "two passwords do not match";
            }else message = "wrong password";
        }else message = "fill all fields";

        Notifications.create().title("Info").text(message).showInformation();
    }
}
