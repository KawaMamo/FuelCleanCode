package com.example.desktop.seller;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.seller.SellerService;
import com.example.model.tools.FormType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Region;
import org.example.model.Seller;

import java.util.List;
import java.util.Objects;

public class Sellers implements TableController {
    @FXML
    private TextField page;

    @FXML
    private TableView<Seller> tableTbl;

    private final SellerService service = SellerService.getInstance();
    private static ObservableList<Seller> observableList;
    public static Seller selectedSeller;
    private String query = null;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, Seller, t1) -> selectedSeller = t1);
    }

    private void setTable() {
        TableColumn<Seller, String> idColumn = new TableColumn<>("التسلسل");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Seller, String> nameColumn = new TableColumn<>("الاسم");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Seller, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(
                new PropertyValueFactory<>("createdAt"));

        TableColumn<Seller, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(
                new PropertyValueFactory<>("updatedAt"));


        tableTbl.getColumns().addAll(idColumn, nameColumn, createdAtCol, updatedAtCol);
        tableTbl.setItems(observableList);
    }

    @FXML
    void add() {
        AddSeller.formType = FormType.CREATE;
        AddSeller.controller = this;
        Modal.start(this.getClass(), "addSeller.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.deleteUrl = service.getEndPoint();
        DeleteConfirmation.selected = selectedSeller.getId();
        DeleteConfirmation.controller = this;
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddSeller.formType = FormType.UPDATE;
        AddSeller.controller = this;
        Modal.start(this.getClass(), "addSeller.fxml");
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
        AddSeller.formType = FormType.GET;
        AddSeller.controller = this;
        Modal.start(this.getClass(), "addSeller.fxml");
    }

    @Override
    public void removeData() {
        observableList.remove(selectedSeller);
        loadData();
    }

    @Override
    public void addData(Object object) {
        observableList.add((Seller) object);
        loadData();
    }

    @Override
    public void loadData() {
        final List<Seller> items;
        if(Objects.isNull(query))
            items = service.getItems(Integer.parseInt(page.getText()) - 1, 15);
        else
            items = service.getItems(Integer.parseInt(page.getText()) - 1, 15, query);

        if(Objects.nonNull(items))
            observableList = FXCollections.observableArrayList(items);
        else observableList = null;
        tableTbl.setItems(observableList);
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }
}
