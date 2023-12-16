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
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.Region;

import java.io.*;
import java.time.LocalDate;
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
    private ToggleButton htmlTB;
    @FXML
    private ToggleButton xlsxTB;

    @FXML
    private ToggleButton normalTB;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, Region, t1) -> selectedRegion = t1);

        final ToggleGroup exportTypeGroup = new ToggleGroup();
        xlsxTB.setToggleGroup(exportTypeGroup);
        htmlTB.setToggleGroup(exportTypeGroup);
        htmlTB.setSelected(true);

        final ToggleGroup transTypeGroup = new ToggleGroup();
        normalTB.setToggleGroup(transTypeGroup);
        commercialTB.setToggleGroup(transTypeGroup);
        normalTB.setSelected(true);
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
        final byte[] bytes = partitionService.getReport(file.getName().split("\\.")[1].toUpperCase(),
                "NORMAL",
                LocalDate.parse("2000-01-01"),
                LocalDate.parse("2024-01-01"),
                selectedRegion.getId());

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(Base64.getDecoder().decode(bytes));
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
