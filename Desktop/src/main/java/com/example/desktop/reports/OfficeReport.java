package com.example.desktop.reports;

import com.example.desktop.HelloApplication;
import com.example.model.TableController;
import com.example.model.office.OfficeService;
import com.example.model.transLog.TransLogService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.controlsfx.control.Notifications;
import org.example.model.Office;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class OfficeReport implements TableController {

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

    @FXML
    private TableView<Office> tableTbl;

    private static ObservableList<Office> offices;
    public static Office slectedOffice;
    private final OfficeService officeService = OfficeService.getInstance();
    private final TransLogService transLogService = TransLogService.getInstance();
    private String query = null;
    final ToggleGroup transTypeGroup = new ToggleGroup();

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, Office, t1) -> slectedOffice = t1);

        normalTB.setToggleGroup(transTypeGroup);
        commercialTB.setToggleGroup(transTypeGroup);
        normalTB.setSelected(true);
        normalTB.setId("normal");
    }

    private void setTable(){
        TableColumn<Office, String> idColumn = new TableColumn<>("التسلسل");
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));

        TableColumn<Office, String> nameColumn = new TableColumn<>("الاسم");
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        TableColumn<Office, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(
                new PropertyValueFactory<>("createdAt"));

        TableColumn<Office, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(
                new PropertyValueFactory<>("updateAt"));
        tableTbl.getColumns().addAll(idColumn, nameColumn, createdAtCol, updatedAtCol);
        tableTbl.setItems(offices);
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
    void report() {
        if(Objects.isNull(slectedOffice) || Objects.isNull(startDP.getValue()) || Objects.isNull(endDP.getValue())){
            Notifications.create().text("يرجى اختيار عنصر وتحديد تاريخي البدء ةالانتهاء").title("chosse something").showInformation();
        }else {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"));
            final File file = fileChooser.showSaveDialog(HelloApplication.primaryStage);
            String transType = normalTB.isSelected() ? "NORMAL":"COMMERCIAL";
            final byte[] bytes = transLogService.getOfficeReport(file.getName().split("\\.")[1].toUpperCase(),
                    transType,
                    startDP.getValue(),
                    endDP.getValue(),
                    slectedOffice.getId());
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(Base64.getDecoder().decode(bytes));
                if(!List.of(Objects.requireNonNull(file.getParentFile().list())).contains("OfficeReport.html_files")){
                    final File waveFile = new File("Desktop/src/main/resources/icon/wave.png");
                    final File logoFile = new File("Desktop/src/main/resources/icon/sadLogo.png");
                    final FileInputStream waveInputStream = new FileInputStream(waveFile);
                    final FileInputStream logoInputStream = new FileInputStream(logoFile);
                    final File outputFile = new File(file.getParentFile() + "/OfficeReport.html_files/img_0_0_2.png");
                    final File logOutput = new File(file.getParentFile() + "/OfficeReport.html_files/img_0_0_0.png");
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

    @Override
    public void removeData() {

    }

    @Override
    public void addData(Object object) {

    }

    @Override
    public void loadData() {
        final List<Office> items;
        if(Objects.isNull(query)){
            items = officeService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        }else {
            items = officeService.getItems(Integer.parseInt(page.getText()) - 1, 15, query);
        }

        if(Objects.nonNull(items))
            offices = FXCollections.observableArrayList(items);
        else offices = null;
        tableTbl.setItems(offices);
    }

    @Override
    public void setQuery(String query) {

    }
}
