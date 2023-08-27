package com.example.desktop.user;

import com.example.model.modal.Modal;
import com.example.model.user.User;
import com.example.model.user.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class Users {

    @FXML
    private TableView<User> usersTbl;
    @FXML
    private TextField page;
    public static ObservableList<User> users;

    UserService userService = UserService.getInstance();
    private User selectedUser;

    @FXML
    private void initialize(){
        loadData();
        setTable();

        usersTbl.getSelectionModel().selectedItemProperty()
                .addListener((observableValue, user, t1) -> {
            selectedUser = t1;
        });
    }

    private void loadData() {
        users = FXCollections.observableArrayList(userService.getUsers(Integer.parseInt(
                page.getText())-1,
                12,
                "",
                "email",
                "asc"));
    }

    @FXML
    void activate() {
        // filter out the selected user
        final FilteredList<User> filtered = users.filtered(user -> !user.equals(selectedUser));
        // create new List that supports addition
        List<User> userList = new ArrayList<>(filtered);
        selectedUser.setIsActive(1);
        userService.block(selectedUser, false);
        // add selected item to list after update
        userList.add(selectedUser);
        // reload users
        users.clear();
        users.addAll(userList);
    }

    @FXML
    void add() {
        Modal.start(Users.class, "addUser.fxml");
    }

    @FXML
    void deactivate() {
        // filter out the selected user
        final FilteredList<User> filtered = users.filtered(user -> !user.equals(selectedUser));
        // create new List that supports addition
        List<User> userList = new ArrayList<>(filtered);
        selectedUser.setIsActive(0);
        userService.block(selectedUser, true);
        // add selected item to list after update
        userList.add(selectedUser);
        // reload users
        users.clear();
        users.addAll(userList);
    }

    private void setTable(){
        TableColumn<User, String> idCol = new TableColumn<>("التسلسل");
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        TableColumn<User, String> emailCol = new TableColumn<>("اسم المستخدم");
        emailCol.setCellValueFactory(
                new PropertyValueFactory<>("email"));

        TableColumn<User, String> roleCol = new TableColumn<>("نوع الحساب");
        roleCol.setCellValueFactory(
                new PropertyValueFactory<>("role"));

        TableColumn<User, String> activationCol = new TableColumn<>("الحالة");
        activationCol.setCellValueFactory(
                new PropertyValueFactory<>("isActive"));
        usersTbl.getColumns().addAll(idCol, emailCol, roleCol, activationCol);
        usersTbl.setItems(users);
    }

    @FXML
    private void pageUp(){
        int newPage = Integer.parseInt(page.getText())+1;
        page.setText(String.valueOf(newPage));
        usersTbl.getItems().clear();
        loadData();
        usersTbl.setItems(users);
    }

    @FXML
    private void pageDown(){
        if(Integer.parseInt(page.getText())>1){
            int newPage = Integer.parseInt(page.getText())-1;
            page.setText(String.valueOf(newPage));
            loadData();
            usersTbl.setItems(users);
        }
    }

}
