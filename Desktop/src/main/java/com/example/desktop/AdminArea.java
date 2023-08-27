package com.example.desktop;


import com.example.desktop.vehicles.Vehicles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class AdminArea {

    @FXML
    private HBox horizontalBar;


    @FXML
    private GridPane workArea;

    @FXML
    private void initialize(){

        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("user/userSection.fxml"));

        try {
            horizontalBar.getChildren().add(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void categories() {

    }

    @FXML
    void gasStations() {

    }

    @FXML
    void groups() {

    }

    @FXML
    void transLines() {

    }

    @FXML
    void users() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("user/users.fxml"));
        try {
            workArea.getChildren().clear();
            workArea.getChildren().add(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void vehicles() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("vehicles/vehicles.fxml"));
        try {
            final Node node = loader.load();
            final Vehicles controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
