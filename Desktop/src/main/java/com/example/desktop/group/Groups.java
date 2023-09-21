package com.example.desktop.group;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.group.GroupService;
import com.example.model.modal.Modal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Group;

import java.util.List;
import java.util.Objects;

public class Groups implements TableController {
    @FXML
    private TextField page;

    @FXML
    private TableView<Group> tableTbl;
    private static ObservableList<Group> groups;
    public static Group selectedGroup;
    private final GroupService groupService = GroupService.getInstance();


    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, GasStation, t1) -> selectedGroup = t1);
    }

    @FXML
    void add() {
        AddGroup.controller = this;
        AddGroup.isEditingForm = false;
        Modal.start(this.getClass(), "addGroup.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.deleteUrl = groupService.getEndPoint();
        DeleteConfirmation.selected = selectedGroup.getId();
        DeleteConfirmation.controller = this;
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddGroup.controller = this;
        AddGroup.isEditingForm = true;
        Modal.start(this.getClass(), "addGroup.fxml");
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
    @Override
    public void removeData() {
        groups.remove(selectedGroup);
        loadData();
    }

    @Override
    public void addData(Object object) {
        groups.add((Group) object);
        loadData();
    }

    public void loadData() {
        final List<Group> items = groupService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        if(Objects.nonNull(items))
            groups = FXCollections.observableArrayList(items);
        else groups = null;
        tableTbl.setItems(groups);
    }

    private void setTable() {
        TableColumn<Group, String> idCol = new TableColumn<>("التسلسل");
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        TableColumn<Group, String> nameCol = new TableColumn<>("الاسم");
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn<Group, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(
                new PropertyValueFactory<>("createdAt"));

        TableColumn<Group, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(
                new PropertyValueFactory<>("updatedAt"));

        tableTbl.getColumns().addAll(idCol, nameCol, createdAtCol, updatedAtCol);
        tableTbl.setItems(groups);
    }
}
