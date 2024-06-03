package com.example.desktop.transportation;

import com.example.model.TableController;
import com.example.model.transportation.TransportationService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.example.model.Transportation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TransScan implements TableController {

    @FXML
    private TableView<?> fileTBL;

    @FXML
    private TextField page;

    @FXML
    private TableView<Transportation> tableTbl;
    private static ObservableList<Transportation> observableList;
    public static Transportation selectedTransportation;
    private final TransportationService transportationService = TransportationService.getInstance();
    private String query = null;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, Transportation, t1) -> selectedTransportation = t1);
    }

    @Override
    public void removeData() {

    }

    @Override
    public void addData(Object object) {

    }

    @Override
    public void loadData() {
        final List<Transportation> items;
        if(Objects.isNull(query))
            items = transportationService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        else items = transportationService.getItems(Integer.parseInt(page.getText()) - 1, 15, query);

        if(Objects.nonNull(items))
            observableList = FXCollections.observableList(items);
        tableTbl.setItems(observableList);
    }

    private void setTable() {

        TableColumn<Transportation, String> idCol = new TableColumn<>("id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Transportation, String> vehicleCol = new TableColumn<>("vehicle");
        vehicleCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVehicle().getPlateNumber()+" "+
                data.getValue().getVehicle().getDriver().getName()));

        TableColumn<Transportation, String> refineryCol = new TableColumn<>("refinery");
        refineryCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRefinery().getName()));

        TableColumn<Transportation, String> sizeCol = new TableColumn<>("size");
        sizeCol.setCellValueFactory(data -> new SimpleStringProperty(NumberFormat.getInstance().format(data.getValue().getSize())));

        TableColumn<Transportation, String> partitionsCol = new TableColumn<>("partitions");
        partitionsCol.setCellValueFactory(
                data -> {
                    StringBuilder partitionString = new StringBuilder();
                    if(Objects.nonNull(data.getValue().getPartitions())){
                        data.getValue().getPartitions().forEach(partition -> partitionString.append(partition.getGasStation().getName()).append(" "));
                        return new SimpleStringProperty(partitionString.toString());
                    }else return new SimpleStringProperty("NA");
                }
        );

        tableTbl.getColumns().addAll(idCol, vehicleCol, refineryCol, sizeCol, partitionsCol);
        tableTbl.setItems(observableList);
    }

    @Override
    public void setQuery(String query) {

    }

    @FXML
    void pageDown() {
        page.setText(String.valueOf(Integer.parseInt(page.getText())-1));
        loadData();
    }

    @FXML
    void pageUp() {
        page.setText(String.valueOf(Integer.parseInt(page.getText())+1));
        loadData();
    }

    @FXML
    void search() {

    }

    @FXML
    void upload() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterPNG, extFilterpng);
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try (FileInputStream fin = new FileInputStream(file)) {
            final byte[] bytes = fin.readAllBytes();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void preview() {

    }
}
