package com.example.desktop.person;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.desktop.transLine.TransLines;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.person.PersonService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.*;

public class Persons implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<Person> tableTbl;

    private static ObservableList<Person> people;
    public static Person selectedPerson;
    private final PersonService personService = PersonService.getInstance();

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, GasStation, t1) -> selectedPerson = t1);
    }

    @FXML
    void add() {
        AddPerson.controller = this;
        Modal.start(this.getClass(), "addPerson.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.controller = this;
        DeleteConfirmation.selected = selectedPerson.getId();
        DeleteConfirmation.deleteUrl = personService.getEndPoint();
        Modal.start(TransLines.class, "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddPerson.controller = this;
        AddPerson.isEditingForm = true;
        Modal.start(this.getClass(), "addPerson.fxml");
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
        people.remove(selectedPerson);
        loadData();
    }

    @Override
    public void addData(Object object) {
        people.add((Person) object);
        loadData();
    }

    @Override
    public void loadData() {
        people = FXCollections.observableArrayList(personService.getItems(null, null));
        tableTbl.setItems(people);
    }

    private void setTable() {
        TableColumn<Person, String> idCol = new TableColumn<>("التسلسل");
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        TableColumn<Person, String> nameCol = new TableColumn<>("الاسم");
        nameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        TableColumn<Person, String> fatherCol = new TableColumn<>("الأب");
        fatherCol.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getFather()));

        TableColumn<Person, String> motherCol = new TableColumn<>("الأم");
        motherCol.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getMother()));

        TableColumn<Person, String> nationalIdCol = new TableColumn<>("الرقم الوطني");
        nationalIdCol.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNationalId()));

        TableColumn<Person, String> birthPlaceCol = new TableColumn<>("الميلاد");
        birthPlaceCol.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getBirthPlace()));

        TableColumn<Person, String> birthDateCol = new TableColumn<>("مكان الولادة");
        birthDateCol.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getBirthPlace()));

        TableColumn<Person, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(
                new PropertyValueFactory<>("createdAt"));

        TableColumn<Person, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(
                new PropertyValueFactory<>("updatedAt"));

        tableTbl.getColumns().addAll(idCol,
                nameCol,
                fatherCol,
                motherCol,
                nationalIdCol,
                birthDateCol,
                birthPlaceCol,
                createdAtCol,
                updatedAtCol);
        tableTbl.setItems(people);
    }
}
