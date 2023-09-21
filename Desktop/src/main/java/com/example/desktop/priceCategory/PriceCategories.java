package com.example.desktop.priceCategory;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.priceCategory.PriceCategoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.PriceCategory;

import java.util.List;
import java.util.Objects;

public class PriceCategories implements TableController {
    @FXML
    private TextField page;

    @FXML
    private TableView<PriceCategory> tableTbl;
    private ObservableList<PriceCategory> priceCategories;
    public static PriceCategory selectdPriceCategory;
    private final PriceCategoryService priceCategoryService = PriceCategoryService.getInstance();

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, GasStation, t1) -> selectdPriceCategory = t1);
    }
    @FXML
    void add() {
        AddPriceCategory.controller = this;
        AddPriceCategory.isEditingForm = false;
        Modal.start(this.getClass(), "addPriceCategory.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.deleteUrl = priceCategoryService.getEndPoint();
        DeleteConfirmation.selected = selectdPriceCategory.getId();
        DeleteConfirmation.controller = this;
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddPriceCategory.controller = this;
        AddPriceCategory.isEditingForm = true;
        Modal.start(this.getClass(), "addPriceCategory.fxml");
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
        priceCategories.remove(selectdPriceCategory);
        loadData();
    }

    @Override
    public void addData(Object object) {
        priceCategories.add((PriceCategory) object);
        loadData();
    }

    public void loadData() {
        final List<PriceCategory> items = priceCategoryService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        if(Objects.nonNull(items))
            priceCategories = FXCollections.observableArrayList(items);
        else priceCategories = null;
        tableTbl.setItems(priceCategories);
    }

    private void setTable() {
        TableColumn<PriceCategory, String> idCol = new TableColumn<>("التسلسل");
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        TableColumn<PriceCategory, String> nameCol = new TableColumn<>("الاسم");
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn<PriceCategory, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(
                new PropertyValueFactory<>("createdAt"));

        TableColumn<PriceCategory, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(
                new PropertyValueFactory<>("updatedAt"));

        tableTbl.getColumns().addAll(idCol, nameCol, createdAtCol, updatedAtCol);
        tableTbl.setItems(priceCategories);
    }
}
