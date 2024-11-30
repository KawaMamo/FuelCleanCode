package com.example.desktop;

import com.example.desktop.clientPayment.ClientPayments;
import com.example.desktop.officePayment.OfficePayments;
import com.example.desktop.paidToParent.PaidToParents;
import com.example.desktop.returnedMaterial.ReturnedMaterials;
import com.example.desktop.sellerPayment.SellerPayments;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class FinancialArea {

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
    void clientPayment() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("clientPayment/clientPayments.fxml"));
        try {
            final Node node = loader.load();
            final ClientPayments controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void officePayment() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("officePayment/officePayments.fxml"));
        try {
            final Node node = loader.load();
            final OfficePayments controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void paidToParent() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("paidToParent/paidToParents.fxml"));
        try {
            final Node node = loader.load();
            final PaidToParents controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
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
    void sellerPayment() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sellerPayment/sellerPayments.fxml"));
        try {
            final Node node = loader.load();
            final SellerPayments controller = loader.getController();
            workArea.getChildren().clear();
            workArea.getChildren().add(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
