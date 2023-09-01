package com.example.desktop;


import com.example.desktop.gasStation.GasStations;
import com.example.desktop.group.Groups;
import com.example.desktop.priceCategory.PriceCategories;
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("priceCategory/priceCategories.fxml"));
        try {
            final Node node = loader.load();
            final PriceCategories controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void gasStations() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("gasStation/gasStations.fxml"));
        try {
            final Node node = loader.load();
            final GasStations controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void groups() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("group/groups.fxml"));
        try {
            final Node node = loader.load();
            final Groups controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
