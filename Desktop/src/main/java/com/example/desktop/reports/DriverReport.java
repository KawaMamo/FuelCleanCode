package com.example.desktop.reports;

import com.example.desktop.HelloApplication;
import com.example.desktop.vehicles.AddVehicle;
import com.example.desktop.vehicles.Vehicles;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.tools.FormType;
import com.example.model.transLog.TransLogService;
import com.example.model.vehicle.VehicleService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.controlsfx.control.Notifications;
import org.example.model.Vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class DriverReport implements TableController {

    @FXML
    private ToggleButton commercialTB;

    @FXML
    private DatePicker endDP;

    @FXML
    private ToggleButton normalTB;

    @FXML
    private TextField page;

    @FXML
    private DatePicker startDP;
    private String query = null;

    @FXML
    private TableView<Vehicle> tableTbl;
    private static ObservableList<Vehicle> vehicles;
    private final VehicleService vehicleService = VehicleService.getInstance();
    public static Vehicle selectedVehicle;
    private final TransLogService transLogService = TransLogService.getInstance();

    final ToggleGroup transTypeGroup = new ToggleGroup();
    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, vehicle, t1) -> selectedVehicle = t1);

        normalTB.setToggleGroup(transTypeGroup);
        commercialTB.setToggleGroup(transTypeGroup);
        normalTB.setSelected(true);
        normalTB.setId("normal");
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
    void search(){
        AddVehicle.controller = this;
        AddVehicle.formType = FormType.GET;
        Modal.start(Vehicles.class, "addVehicle.fxml");
    }

    @FXML
    void report() {
        if(Objects.isNull(selectedVehicle) || Objects.isNull(startDP.getValue()) || Objects.isNull(endDP.getValue())){
            Notifications.create().text("يرجى اختيار عنصر وتحديد تاريخي البدء ةالانتهاء").title("chosse something").showInformation();
        }else {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"));
            final File file = fileChooser.showSaveDialog(HelloApplication.primaryStage);
            String transType = normalTB.isSelected() ? "NORMAL":"COMMERCIAL";
            final byte[] bytes = transLogService.getReport(file.getName().split("\\.")[1].toUpperCase(),
                    transType,
                    startDP.getValue(),
                    endDP.getValue(),
                    selectedVehicle.getId());

            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(Base64.getDecoder().decode(bytes));
                if(!List.of(Objects.requireNonNull(file.getParentFile().list())).contains("DriverReport.html_files")){
                    final File waveFile = new File("Desktop/src/main/resources/icon/wave.png");
                    final File logoFile = new File("Desktop/src/main/resources/icon/sadLogo.png");
                    final FileInputStream waveInputStream = new FileInputStream(waveFile);
                    final FileInputStream logoInputStream = new FileInputStream(logoFile);
                    final File outputFile = new File(file.getParentFile() + "/DriverReport.html_files/img_0_0_2.png");
                    final File logOutput = new File(file.getParentFile() + "/DriverReport.html_files/img_0_0_0.png");
                    outputFile.getParentFile().mkdir();
                    outputFile.createNewFile();
                    logOutput.createNewFile();
                    final FileOutputStream waveOutputStream = new FileOutputStream(outputFile);
                    final FileOutputStream logoOutPutStream = new FileOutputStream(logOutput);
                    int info = 0;
                    while( (info = waveInputStream.read()) != -1) {
                        waveOutputStream.write(info);
                    }
                    int info2 = 0;
                    while ((info2 = logoInputStream.read()) != -1){
                        logoOutPutStream.write(info2);
                    }
                    waveOutputStream.close();
                    waveInputStream.close();
                    logoInputStream.close();
                    logoOutPutStream.close();
                }
                Runtime.getRuntime().exec("rundll32.exe shell32.dll ShellExec_RunDLL " +file.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void setTable() {
        TableColumn<Vehicle, String> idCol = new TableColumn<>("التسلسل");
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        TableColumn<Vehicle, String> plateNumberCol = new TableColumn<>("رقم اللوحة");
        plateNumberCol.setCellValueFactory(
                new PropertyValueFactory<>("plateNumber"));

        TableColumn<Vehicle, String> trafficCenterCol = new TableColumn<>("مرور");
        trafficCenterCol.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getTrafficCenter().getName())
        );

        TableColumn<Vehicle, String> sizeCol = new TableColumn<>("الحجم");
        sizeCol.setCellValueFactory(
                new PropertyValueFactory<>("size"));

        TableColumn<Vehicle, String> officeCol = new TableColumn<>("مكتب");
        officeCol.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getOffice().getName()));

        TableColumn<Vehicle, String> driverCol = new TableColumn<>("السائق");
        driverCol.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getDriver().getName()));

        TableColumn<Vehicle, String> turnCol = new TableColumn<>("الدور");
        turnCol.setCellValueFactory(new PropertyValueFactory<>("turn"));

        TableColumn<Vehicle, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(
                new PropertyValueFactory<>("createdAt"));

        TableColumn<Vehicle, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(
                new PropertyValueFactory<>("updatedAt"));

        tableTbl.getColumns().addAll(idCol, plateNumberCol, trafficCenterCol, sizeCol, officeCol, driverCol, turnCol, createdAtCol, updatedAtCol);
        tableTbl.setItems(vehicles);
    }

    @Override
    public void removeData() {
        vehicles.remove(selectedVehicle);
        loadData();
    }

    @Override
    public void addData(Object object) {
        vehicles.add((Vehicle) object);
        loadData();
    }

    public void loadData() {
        final List<Vehicle> vehicleList;
        if(Objects.isNull(query))
            vehicleList = vehicleService.getVehicles(Integer.parseInt(page.getText()) - 1, 15);
        else
            vehicleList = vehicleService.getVehicles(Integer.parseInt(page.getText()) - 1, 15, query);
        if(Objects.nonNull(vehicleList))
            vehicles = FXCollections.observableArrayList(vehicleList);
        tableTbl.setItems(vehicles);
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }
}
