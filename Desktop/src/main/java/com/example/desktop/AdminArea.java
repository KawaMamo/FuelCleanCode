package com.example.desktop;


import com.example.desktop.gasStation.GasStations;
import com.example.desktop.group.Groups;
import com.example.desktop.office.Offices;
import com.example.desktop.person.Persons;
import com.example.desktop.priceCategory.PriceCategories;
import com.example.desktop.refinery.Refineries;
import com.example.desktop.region.Regions;
import com.example.desktop.trafficCenter.TrafficCenters;
import com.example.desktop.transLine.TransLines;
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("transLine/transLines.fxml"));
        try {
            final Node node = loader.load();
            final TransLines controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    @FXML
    void offices() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("office/offices.fxml"));
        try {
            final Node node = loader.load();
            final Offices controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void persons() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("person/persons.fxml"));
        try {
            final Node node = loader.load();
            final Persons controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void trafficCenters() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("trafficCenter/trafficCenters.fxml"));
        try {
            final Node node = loader.load();
            final TrafficCenters controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void regions() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("region/regions.fxml"));
        try {
            final Node node = loader.load();
            final Regions controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void refineries(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("refinery/refineries.fxml"));
        try {
            final Node node = loader.load();
            final Refineries controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
