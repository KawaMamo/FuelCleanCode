package com.example.desktop.reports;

import com.example.desktop.HelloApplication;
import com.example.model.partition.PartitionService;
import com.example.model.region.RegionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.example.model.Region;

import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class RegionReport {
    @FXML
    private TextField page;

    @FXML
    private TableView<Region> tableTbl;

    private static ObservableList<Region> regions;
    public static Region selectedRegion;
    private final RegionService regionService = RegionService.getInstance();

    private final PartitionService partitionService = PartitionService.getINSTANCE();
    @FXML
    private ToggleButton commercialTB;

    @FXML
    private DatePicker endDP;
    @FXML
    private DatePicker startDP;

    @FXML
    private ToggleButton normalTB;
    final ToggleGroup transTypeGroup = new ToggleGroup();

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, Region, t1) -> selectedRegion = t1);

        normalTB.setToggleGroup(transTypeGroup);
        commercialTB.setToggleGroup(transTypeGroup);
        normalTB.setSelected(true);
        normalTB.setId("normal");
    }

    @FXML
    void pageDown() {

    }

    @FXML
    void pageUp() {

    }

    @FXML
    void report() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"));
        final File file = fileChooser.showSaveDialog(HelloApplication.primaryStage);
        String transType = normalTB.isSelected() ? "NORMAL":"COMMERCIAL";
        final byte[] bytes = partitionService.getReport(file.getName().split("\\.")[1].toUpperCase(),
                transType,
                startDP.getValue(),
                endDP.getValue(),
                selectedRegion.getId());

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(Base64.getDecoder().decode(bytes));
            Runtime.getRuntime().exec("rundll32.exe shell32.dll ShellExec_RunDLL " +file.getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadData() {
        final List<Region> items = regionService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        if(Objects.nonNull(items))
            regions = FXCollections.observableArrayList(items);
        else regions = null;
        tableTbl.setItems(regions);
    }

    private void setTable() {
        TableColumn<Region, String> idColumn = new TableColumn<>("التسلسل");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Region, String> nameColumn = new TableColumn<>("الاسم");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Region, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(
                new PropertyValueFactory<>("createdAt"));

        TableColumn<Region, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(
                new PropertyValueFactory<>("updatedAt"));
        tableTbl.getColumns().addAll(idColumn, nameColumn, createdAtCol, updatedAtCol);
        tableTbl.setItems(regions);
    }
}
