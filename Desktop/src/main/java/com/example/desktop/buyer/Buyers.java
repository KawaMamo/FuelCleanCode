package com.example.desktop.buyer;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.buyer.BuyerService;
import com.example.model.modal.Modal;
import com.example.model.tools.FormType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.model.Buyer;

import java.util.List;
import java.util.Objects;

public class Buyers implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<Buyer> tableTbl;
    private static ObservableList<Buyer> buyerObservableList;
    public static Buyer selectedBuyer;
    private String query = null;
    private final BuyerService buyerService = BuyerService.getInstance();

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Buyer>() {
            @Override
            public void changed(ObservableValue<? extends Buyer> observableValue, Buyer buyer, Buyer t1) {
                selectedBuyer = t1;
            }
        });
    }

    private void setTable(){
        TableColumn<Buyer, String> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId().toString()));

        TableColumn<Buyer, String> nameColumn = new TableColumn<>("الاسم");
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        TableColumn<Buyer, String> organizationColumn = new TableColumn<>("المؤسسة");
        organizationColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrganization()));

        TableColumn<Buyer, String> createdAtColumn = new TableColumn<>("تاريخ الإضافة");
        createdAtColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCreatedAt().toString()));

        TableColumn<Buyer, String> updatedAtColumn = new TableColumn<>("تاريخ التعديل");
        updatedAtColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUpdatedAt().toString()));

        tableTbl.getColumns().addAll(idColumn, nameColumn, organizationColumn, createdAtColumn, updatedAtColumn);
        tableTbl.setItems(buyerObservableList);
    }

    @FXML
    void add() {
        AddBuyer.formType = FormType.CREATE;
        AddBuyer.controller = this;
        Modal.start(this.getClass(), "addBuyer.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.deleteUrl = buyerService.getEndPoint();
        DeleteConfirmation.selected = selectedBuyer.getId();
        DeleteConfirmation.controller = this;
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddBuyer.formType = FormType.UPDATE;
        AddBuyer.controller = this;
        Modal.start(this.getClass(), "addBuyer.fxml");
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
        AddBuyer.formType = FormType.GET;
        AddBuyer.controller = this;
        Modal.start(this.getClass(), "addBuyer.fxml");
    }

    @Override
    public void removeData() {
        buyerObservableList.remove(selectedBuyer);
        loadData();
    }

    @Override
    public void addData(Object object) {
        buyerObservableList.add((Buyer) object);
        loadData();
    }

    @Override
    public void loadData() {
        List<Buyer> items;
        if(Objects.isNull(query)) {
            items = buyerService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        }else {
            items = buyerService.getItems(Integer.parseInt(page.getText()) - 1, 15, query);
        }
        if(Objects.nonNull(items)){
            buyerObservableList = FXCollections.observableList(items);
        }else buyerObservableList = null;
        tableTbl.setItems(buyerObservableList);
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }
}
