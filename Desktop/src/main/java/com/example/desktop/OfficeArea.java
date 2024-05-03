package com.example.desktop;

import com.example.desktop.person.Persons;
import com.example.desktop.reports.DriverReport;
import com.example.desktop.reports.RefineryReport;
import com.example.desktop.reports.RegionReport;
import com.example.desktop.trafficCenter.TrafficCenters;
import com.example.desktop.transportation.Transportations;
import com.example.desktop.vehicles.Vehicles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class OfficeArea {

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
    void transportations() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("transportation/transportations.fxml"));
        try {
            final Node node = loader.load();
            final Transportations controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void driverReport() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("reports/driverReport.fxml"));
        try {
            final Node node = loader.load();
            final DriverReport controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void regionReport() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("reports/regionReport.fxml"));
        try {
            final Node node = loader.load();
            final RegionReport controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
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
    void refineryReport(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("reports/refineryReport.fxml"));
        try {
            final Node node = loader.load();
            final RefineryReport controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
