package com.example.desktop.person;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.desktop.transLine.TransLines;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.person.PersonService;
import com.example.model.tools.FormType;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.*;

import java.util.List;
import java.util.Objects;

public class Persons implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<Person> tableTbl;

    private static ObservableList<Person> people;
    public static Person selectedPerson;
    private final PersonService personService = PersonService.getInstance();
    private String query = null;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, GasStation, t1) -> selectedPerson = t1);
    }

    @FXML
    void add() {
        AddPerson.controller = this;
        AddPerson.formType = FormType.CREATE;
        Modal.start(this.getClass(), "addPerson.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.controller = this;
        DeleteConfirmation.selected = selectedPerson.getId();
        DeleteConfirmation.deleteUrl = personService.getEndPoint();
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddPerson.controller = this;
        AddPerson.formType = FormType.UPDATE;
        Modal.start(this.getClass(), "addPerson.fxml");
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
        AddPerson.controller = this;
        AddPerson.formType = FormType.GET;
        Modal.start(this.getClass(), "addPerson.fxml");
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
        final List<Person> items;
        if(Objects.isNull(query)){
            items = personService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        }else {
            items = personService.getItems(Integer.parseInt(page.getText()) - 1, 15, query);
        }

        if(Objects.nonNull(items))
            people = FXCollections.observableArrayList(items);
        else people = null;
        tableTbl.setItems(people);
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }

    private void setTable() {
        TableColumn<Person, String> idCol = new TableColumn<>("التسلسل");
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        TableColumn<Person, String> nameCol = new TableColumn<>("الاسم");
        nameCol.setCellValueFactory(data -> {
            if(Objects.nonNull(data)){
                return new SimpleStringProperty(data.getValue().getName());
            }else {
                return new SimpleStringProperty();
            }

        });

        TableColumn<Person, String> fatherCol = new TableColumn<>("الأب");
        fatherCol.setCellValueFactory(data-> {
            if(Objects.nonNull(data)){
                return new SimpleStringProperty(data.getValue().getFather());
            }else {
                return new SimpleStringProperty();
            }

        });

        TableColumn<Person, String> motherCol = new TableColumn<>("الأم");
        motherCol.setCellValueFactory(data-> {
            if(Objects.nonNull(data)){
                return new SimpleStringProperty(data.getValue().getMother());
            }else {
                return new SimpleStringProperty();
            }

        });

        TableColumn<Person, String> nationalIdCol = new TableColumn<>("الرقم الوطني");
        nationalIdCol.setCellValueFactory(data-> {
            if(Objects.nonNull(data)){
                return new SimpleStringProperty(data.getValue().getNationalId());
            }else {
                return new SimpleStringProperty();
            }

        });

        /*TableColumn<Person, String> birthPlaceCol = new TableColumn<>("الميلاد");
        birthPlaceCol.setCellValueFactory(data-> {
            if(Objects.nonNull(data)){
                return new SimpleStringProperty(data.getValue().getBirthDate().toString());
            }else {
                return new SimpleStringProperty();
            }

        });

        TableColumn<Person, String> birthDateCol = new TableColumn<>("مكان الولادة");
        birthDateCol.setCellValueFactory(data-> {
            if(Objects.nonNull(data)){
                return new SimpleStringProperty(data.getValue().getBirthPlace());
            }else {
                return new SimpleStringProperty();
            }

        });*/

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
                nationalIdCol/*,
                birthDateCol,
                birthPlaceCol*/,
                createdAtCol,
                updatedAtCol);
        tableTbl.setItems(people);
    }
}
