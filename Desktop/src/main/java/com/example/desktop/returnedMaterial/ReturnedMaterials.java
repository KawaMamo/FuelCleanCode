package com.example.desktop.returnedMaterial;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.returnedMaterial.ReturnedMaterialService;
import com.example.model.tools.FormType;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.model.ReturnedMaterial;

import java.text.NumberFormat;
import java.util.List;
import java.util.Objects;

public class ReturnedMaterials implements TableController {

    @FXML
    private TextField page;
    @FXML
    private TableView<ReturnedMaterial> tableTbl;
    public static ReturnedMaterial selectedReturnedMaterial;
    private static ObservableList<ReturnedMaterial> returnedMaterials;
    private final ReturnedMaterialService returnedMaterialService = ReturnedMaterialService.getInstance();
    private String query = null;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, returnedMaterial, t1) -> selectedReturnedMaterial = t1);
    }

    private void setTable() {
        TableColumn<ReturnedMaterial, String> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(date -> new SimpleStringProperty(date.getValue().getId().toString()));

        TableColumn<ReturnedMaterial, String> gasStationColumn = new TableColumn<>("الكازية");
        gasStationColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGasStation().getName()));

        TableColumn<ReturnedMaterial, String> materialColumn = new TableColumn<>("المادة");
        materialColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMaterial().getName()));

        TableColumn<ReturnedMaterial, String> amountColumn = new TableColumn<>("الكمية");
        amountColumn.setCellValueFactory(data -> new SimpleStringProperty(NumberFormat.getInstance().format(data.getValue().getAmount())));

        TableColumn<ReturnedMaterial, String> priceColumn = new TableColumn<>("السعر");
        priceColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrice().getAmount() + " " +data.getValue().getPrice().getCurrency()));

        TableColumn<ReturnedMaterial, String> centerPriceColumn = new TableColumn<>("سعر المركز");
        centerPriceColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCenterPrice().getAmount()+" "+data.getValue().getCenterPrice().getCurrency()));

        TableColumn<ReturnedMaterial, String> statusColumn = new TableColumn<>("الحالة");
        statusColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));

        TableColumn<ReturnedMaterial, String> buyerColumn = new TableColumn<>("المشتري");
        buyerColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBuyer().getName()+" "+data.getValue().getBuyer().getOrganization()));

        TableColumn<ReturnedMaterial, String> invoiceNoColumn = new TableColumn<>("رقم الايصال");
        invoiceNoColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getInvoiceNo()));

        tableTbl.getColumns().addAll(idColumn, gasStationColumn, materialColumn, amountColumn, priceColumn, centerPriceColumn, statusColumn, buyerColumn, invoiceNoColumn);
        tableTbl.setItems(returnedMaterials);
    }

    @Override
    public void removeData() {
        returnedMaterials.remove(selectedReturnedMaterial);
        loadData();
    }

    @Override
    public void addData(Object object) {
        returnedMaterials.add((ReturnedMaterial) object);
        loadData();
    }

    @Override
    public void loadData() {
        final List<ReturnedMaterial> items;
        if(Objects.isNull(query)){
            items = returnedMaterialService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        }else {
            items = returnedMaterialService.getItems(Integer.parseInt(page.getText()) - 1, 15, query);
        }
        if(Objects.nonNull(items)){
            returnedMaterials = FXCollections.observableArrayList(items);
        }else returnedMaterials = null;
        tableTbl.setItems(returnedMaterials);
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }

    @FXML
    void edit() {
        AddReturnedMaterial.formType = FormType.UPDATE;
        AddReturnedMaterial.controller = this;
        Modal.start(this.getClass(),"addReturnedMaterial.fxml");
    }
    @FXML
    void delete() {
        DeleteConfirmation.deleteUrl = returnedMaterialService.getEndPoint();
        DeleteConfirmation.selected = selectedReturnedMaterial.getId();
        DeleteConfirmation.controller = this;
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }
    @FXML
    void add() {
        AddReturnedMaterial.formType = FormType.CREATE;
        AddReturnedMaterial.controller = this;
        Modal.start(this.getClass(),"addReturnedMaterial.fxml");
    }
    @FXML
    void search() {
        AddReturnedMaterial.formType = FormType.GET;
        AddReturnedMaterial.controller = this;
        Modal.start(this.getClass(),"addReturnedMaterial.fxml");
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
}
