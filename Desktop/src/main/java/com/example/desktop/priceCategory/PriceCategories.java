package com.example.desktop.priceCategory;

import com.example.model.TableController;
import com.example.model.priceCategory.PriceCategoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.PriceCategory;

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

    }

    @FXML
    void delete() {

    }

    @FXML
    void edit() {

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

    }

    @Override
    public void addData(Object object) {

    }

    public void loadData() {
        priceCategories = FXCollections.observableArrayList(priceCategoryService.getItems(null, null));
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
