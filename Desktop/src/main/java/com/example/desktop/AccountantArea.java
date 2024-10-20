package com.example.desktop;

import com.example.desktop.reports.DriverReport;
import com.example.desktop.reports.RefineryReport;
import com.example.desktop.returnedMaterial.ReturnedMaterials;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.io.IOException;

public class AccountantArea {

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
    void returnedMaterials() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("returnedMaterial/returnedMaterials.fxml"));
        try {
            final Node node = loader.load();
            final ReturnedMaterials controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void clientReport(){

    }

    @FXML
    void officeReport(){

    }

    @FXML
    void groupReport(){

    }

    @FXML
    void forfeits(){

    }

    @FXML
    void refineries(){
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

    @FXML
    void driverReport(){
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
}
