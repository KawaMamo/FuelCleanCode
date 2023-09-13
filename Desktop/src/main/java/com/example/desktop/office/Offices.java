package com.example.desktop.office;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.office.OfficeService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Office;

public class Offices implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<Office> tableTbl;

    private static ObservableList<Office> offices;
    public static Office slectedOffice;
    private final OfficeService officeService = OfficeService.getInstance();

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, Office, t1) -> slectedOffice = t1);
    }
    @FXML
    void add() {
        AddOffice.controller = this;
        Modal.start(this.getClass(), "addOffice.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.deleteUrl = officeService.getEndPoint();
        DeleteConfirmation.selected = slectedOffice.getId();
        DeleteConfirmation.controller = this;
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddOffice.controller = this;
        AddOffice.isEditingForm = true;
        Modal.start(this.getClass(), "addOffice.fxml");
    }

    @FXML
    void pageDown() {

    }

    @FXML
    void pageUp() {

    }

    @FXML
    void search() {

    }

    @Override
    public void removeData() {
        offices.remove(slectedOffice);
        loadData();
    }

    @Override
    public void addData(Object object) {
        offices.add((Office) object);
        loadData();
    }

    @Override
    public void loadData() {
        offices = FXCollections.observableArrayList(officeService.getItems(null, null));
        tableTbl.setItems(offices);
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
}
