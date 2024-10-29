package com.example.desktop.clientPayment;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.clientPayment.ClientPaymentService;
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
import org.example.model.ClientPayment;

import java.util.List;
import java.util.Objects;

public class ClientPayments implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<ClientPayment> tableTbl;

    private static ObservableList<ClientPayment> observableList;
    public static ClientPayment selectedClientPayment;
    private String query = null;
    private final ClientPaymentService service = ClientPaymentService.getInstance();

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ClientPayment>() {
            @Override
            public void changed(ObservableValue<? extends ClientPayment> observableValue, ClientPayment clientPayment, ClientPayment t1) {
                selectedClientPayment = t1;
            }
        });
    }

    private void setTable() {
        TableColumn<ClientPayment, String> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId().toString()));

        TableColumn<ClientPayment, String> amountColumn = new TableColumn<>("amount");
        amountColumn.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getAmount().getAmount()+" " + data.getValue().getAmount().getCurrency()));

        TableColumn<ClientPayment, String> billNumberColumn = new TableColumn<>("bill number");
        billNumberColumn.setCellValueFactory(data -> {
            if(Objects.nonNull(data.getValue().getBillNumber()))
                return new SimpleStringProperty(data.getValue().getBillNumber().toString());
            else return new SimpleStringProperty("");
        });

        TableColumn<ClientPayment, String> notesColumn = new TableColumn<>("notes");
        notesColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNotes()));

        TableColumn<ClientPayment, String> gasStationColumn = new TableColumn<>("gasStation");
        gasStationColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGasStation().getName()));

        TableColumn<ClientPayment, String> createdAtColumn = new TableColumn<>("createdAt");
        createdAtColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCreatedAt().toString()));

        TableColumn<ClientPayment, String> updatedAtColumn = new TableColumn<>("updatedAt");
        updatedAtColumn.setCellValueFactory(data ->
                {
                    if(Objects.nonNull(data.getValue().getUpdatedAt()))
                        return new SimpleStringProperty(data.getValue().getUpdatedAt().toString());
                    else return new SimpleStringProperty("");
                }
        );

        tableTbl.getColumns().addAll(idColumn, amountColumn, billNumberColumn, notesColumn, gasStationColumn, createdAtColumn, updatedAtColumn);
        tableTbl.setItems(observableList);
    }

    @FXML
    void add() {
        AddClientPayment.controller = this;
        AddClientPayment.formType = FormType.CREATE;
        Modal.start(this.getClass(), "addClientPayment.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.controller = this;
        DeleteConfirmation.selected = selectedClientPayment.getId();
        DeleteConfirmation.deleteUrl = service.getEndPoint();
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddClientPayment.controller = this;
        AddClientPayment.formType = FormType.UPDATE;
        Modal.start(this.getClass(), "addClientPayment.fxml");
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
        AddClientPayment.controller = this;
        AddClientPayment.formType = FormType.GET;
        Modal.start(this.getClass(), "addClientPayment.fxml");
    }
    @Override
    public void removeData() {
        observableList.remove(selectedClientPayment);
        loadData();
    }

    @Override
    public void addData(Object object) {
        observableList.add((ClientPayment) object);
        loadData();
    }

    @Override
    public void loadData() {
        final List<ClientPayment> items;
        if(Objects.isNull(query)) {
            items = service.getItems(Integer.parseInt(page.getText()) - 1, 15);
        }else {
            items = service.getItems(Integer.parseInt(page.getText()) - 1, 15, query);
        }
        if(Objects.nonNull(items)){
            observableList = FXCollections.observableList(items);
        }else observableList = null;
        tableTbl.refresh();
        tableTbl.setItems(observableList);
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }
}
