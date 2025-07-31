package com.example.desktop.reports;

import com.example.desktop.HelloApplication;
import com.example.model.TableController;
import com.example.model.material.MaterialService;
import com.example.model.partition.PartitionService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.controlsfx.control.Notifications;
import org.example.model.Material;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class DailyReport implements TableController {

    @FXML
    private TextField page;
    @FXML
    private ToggleButton normalTB;
    @FXML
    private ToggleButton commercialTB;
    final ToggleGroup transTypeGroup = new ToggleGroup();
    private String query = null;
    @FXML
    private DatePicker startDP;
    @FXML
    private DatePicker endDP;
    @FXML
    private TableView<Material> tableTbl;
    private static ObservableList<Material> materials;
    private final MaterialService materialService = MaterialService.getInstance();
    private final PartitionService partitionService = PartitionService.getINSTANCE();
    public static Material selectedMaterial;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, Material, t1) -> selectedMaterial = t1);

        normalTB.setToggleGroup(transTypeGroup);
        commercialTB.setToggleGroup(transTypeGroup);
        normalTB.setSelected(true);
        normalTB.setId("normal");
    }

    private void setTable() {
        TableColumn<Material, String> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getId().toString()));

        TableColumn<Material, String> nameCol = new TableColumn<>("name");
        nameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        tableTbl.getColumns().addAll(idColumn, nameCol);
        tableTbl.setItems(materials);
    }
    @Override
    public void removeData() {

    }

    @Override
    public void addData(Object object) {

    }

    @Override
    public void loadData() {
        final List<Material> items;
        if(Objects.isNull(query)){
            items = materialService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        }else {
            items = materialService.getItems(Integer.parseInt(page.getText()) - 1, 15, query);
        }

        if(Objects.nonNull(items))
            materials = FXCollections.observableArrayList(items);
        else materials = null;
        tableTbl.setItems(materials);
    }

    @Override
    public void setQuery(String query) {

    }

    @FXML
    void report(){
        if(Objects.isNull(selectedMaterial) || Objects.isNull(startDP.getValue()) || Objects.isNull(endDP.getValue())){
            Notifications.create().text("يرجى اختيار عنصر وتحديد تاريخي البدء والانتهاء").title("choose something").showInformation();
        }else {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"));
            final File file = fileChooser.showSaveDialog(HelloApplication.primaryStage);
            String transType = normalTB.isSelected() ? "NORMAL" : "COMMERCIAL";
            final byte[] bytes = partitionService.getDailyReport(file.getName().split("\\.")[1].toUpperCase(),
                    transType,
                    startDP.getValue(),
                    endDP.getValue(),
                    selectedMaterial.getId());

            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(Base64.getDecoder().decode(bytes));
                if (!List.of(Objects.requireNonNull(file.getParentFile().list())).contains("DriverReport.html_files")) {
                    final InputStream resourceAsStream = DriverReport.class.getClassLoader().getResourceAsStream("wave.png");
                    final InputStream logoStream = DriverReport.class.getClassLoader().getResourceAsStream("SadLogo.png");

                    final File outputFile = new File(file.getParentFile() + "/DriverReport.html_files/img_0_0_2.png");
                    final File logOutput = new File(file.getParentFile() + "/DriverReport.html_files/img_0_0_0.png");
                    outputFile.getParentFile().mkdir();
                    outputFile.createNewFile();
                    logOutput.createNewFile();
                    final FileOutputStream waveOutputStream = new FileOutputStream(outputFile);
                    final FileOutputStream logoOutPutStream = new FileOutputStream(logOutput);
                    int info = 0;
                    while ((info = resourceAsStream.read()) != -1) {
                        waveOutputStream.write(info);
                    }
                    int info2 = 0;
                    while ((info2 = logoStream.read()) != -1) {
                        logoOutPutStream.write(info2);
                    }
                    waveOutputStream.close();
                    resourceAsStream.close();
                    logoStream.close();
                    logoOutPutStream.close();
                }
                Runtime.getRuntime().exec("rundll32.exe shell32.dll ShellExec_RunDLL " + file.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void search(){

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
}
