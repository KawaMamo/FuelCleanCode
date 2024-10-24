package com.example.desktop.transferMaterial;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.tools.FormType;
import com.example.model.transferMaterial.TransferMaterialsService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.model.TransferMaterials;

import java.text.NumberFormat;
import java.util.List;
import java.util.Objects;

public class TransferMaterialsTblCont implements TableController {
    private static ObservableList<TransferMaterials> transferMaterialsObservableList;
    private final TransferMaterialsService transferMaterialsService = TransferMaterialsService.getInstance();
    private String query = null;
    public static TransferMaterials selectedTransferMaterials;

    @FXML
    private TextField page;

    @FXML
    private TableView<TransferMaterials> tableTbl;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TransferMaterials>() {
            @Override
            public void changed(ObservableValue<? extends TransferMaterials> observableValue, TransferMaterials transferMaterials, TransferMaterials t1) {
                selectedTransferMaterials = t1;
            }
        });
    }

    private void setTable(){
        TableColumn<TransferMaterials, String> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId().toString()));

        TableColumn<TransferMaterials, String> sourceColumn = new TableColumn<>("source");
        sourceColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSource().getName()));

        TableColumn<TransferMaterials, String> destinationColumn = new TableColumn<>("Destination");
        destinationColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDestination().getName()));

        TableColumn<TransferMaterials, String> materialColumn = new TableColumn<>("Material");
        materialColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMaterial().getName()));

        TableColumn<TransferMaterials, String> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(data -> new SimpleStringProperty(NumberFormat.getInstance().format(data.getValue().getAmount())));

        TableColumn<TransferMaterials, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrice().getAmount()+" "+data.getValue().getPrice().getCurrency()));

        TableColumn<TransferMaterials, String> createdAtColumn = new TableColumn<>("CreatedAt");
        createdAtColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCreatedAt().toString()));

        TableColumn<TransferMaterials, String> updatedAtColumn = new TableColumn<>("updatedAt");
        updatedAtColumn.setCellValueFactory(data -> {
            if(Objects.nonNull(data.getValue().getUpdatedAt()))
                return new SimpleStringProperty(data.getValue().getUpdatedAt().toString());
            else return new SimpleStringProperty("");
        });
        tableTbl.getColumns().addAll(idColumn, sourceColumn, destinationColumn, materialColumn, amountColumn, priceColumn, createdAtColumn, updatedAtColumn);
        tableTbl.setItems(transferMaterialsObservableList);
    }

    @FXML
    void add() {
        AddTransferMaterial.formType = FormType.CREATE;
        AddTransferMaterial.controller = this;
        Modal.start(this.getClass(), "addTransferMaterial.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.deleteUrl = transferMaterialsService.getEndPoint();
        DeleteConfirmation.controller = this;
        DeleteConfirmation.selected = selectedTransferMaterials.getId();
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddTransferMaterial.formType = FormType.UPDATE;
        AddTransferMaterial.controller = this;
        Modal.start(this.getClass(), "addTransferMaterial.fxml");
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
        AddTransferMaterial.formType = FormType.GET;
        AddTransferMaterial.controller = this;
        Modal.start(this.getClass(), "addTransferMaterial.fxml");
    }

    @Override
    public void removeData() {
        transferMaterialsObservableList.remove(selectedTransferMaterials);
        loadData();
    }

    @Override
    public void addData(Object object) {
        transferMaterialsObservableList.add((TransferMaterials) object);
        loadData();
    }

    @Override
    public void loadData() {
        List<TransferMaterials> items;
        if(Objects.isNull(query)) {
            items = transferMaterialsService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        }else {
            items = transferMaterialsService.getItems(Integer.parseInt(page.getText()) - 1, 15, query);
        }
        if(Objects.nonNull(items)){
            transferMaterialsObservableList = FXCollections.observableList(items);
        }else transferMaterialsObservableList = null;
        tableTbl.setItems(transferMaterialsObservableList);
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }
}
