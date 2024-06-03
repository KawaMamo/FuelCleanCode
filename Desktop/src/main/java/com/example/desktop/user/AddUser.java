package com.example.desktop.user;

import com.example.model.user.User;
import com.example.model.user.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

public class AddUser {

    @FXML
    private TextField emailTF;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private PasswordField rePasswordTF;

    @FXML
    private ChoiceBox<String> role;

    @FXML
    private Button submitBtn;

    UserService userService = UserService.getInstance();

    @FXML
    private void initialize(){
        role.getItems().add("SUPER-ADMIN");
        role.getItems().add("TRANS_OFFICE");
        role.getItems().add("ACCOUNTANT");
        role.getItems().add("FINANCIAL");
        role.getItems().add("SCANNER");
        submitBtn.setDefaultButton(true);

    }
    @FXML
    void submit() {
        String message;
        if(emailTF.getText().length()>0 &&
                passwordTF.getText().length()>0 &&
                rePasswordTF.getText().length()>0 &&
                role.getValue() != null){
            if(passwordTF.getText().equals(rePasswordTF.getText())){
                User user = new User(null, emailTF.getText(), passwordTF.getText(), role.getValue(), 1);
                User created = userService.addUser(user);
                Users.users.add(created);
                message = "User "+created.getEmail()+" Created successfully";
            }else message = "passwords do not match";
        }else message = "all fields are required";
        Notifications.create().title("Info").text(message).showInformation();
    }
}
