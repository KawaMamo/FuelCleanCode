package com.example.desktop.officePayment;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.officePayment.OfficePaymentService;
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
import org.example.model.OfficePayment;

import java.util.List;
import java.util.Objects;

public class OfficePayments implements TableController {
    @FXML
    private TextField page;

    @FXML
    private TableView<OfficePayment> tableTbl;

    private static ObservableList<OfficePayment> observableList;
    private String query = null;
    private final OfficePaymentService service = OfficePaymentService.getInstance();
    public static OfficePayment selectedOfficePayment;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OfficePayment>() {
            @Override
            public void changed(ObservableValue<? extends OfficePayment> observableValue, OfficePayment officePayment, OfficePayment t1) {
                selectedOfficePayment = t1;
            }
        });
    }

    private void setTable() {

        TableColumn<OfficePayment, String> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId().toString()));

        TableColumn<OfficePayment, String> amountColumn = new TableColumn<>("المبلغ");
        amountColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAmount().getAmount()+" "+data.getValue().getAmount().getCurrency()));

        TableColumn<OfficePayment, String> billNumberColumn = new TableColumn<>("رقم الايصال");
        billNumberColumn.setCellValueFactory(data -> {
            if(Objects.nonNull(data.getValue().getBillNumber()))
                return new SimpleStringProperty(data.getValue().getBillNumber().toString());
            else return new SimpleStringProperty("");
        });

        TableColumn<OfficePayment, String> notesColumn = new TableColumn<>("ملاحظات");
        notesColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNotes()));

        TableColumn<OfficePayment, String> gasStationColumn = new TableColumn<>("Office");
        gasStationColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOffice().getName()));

        TableColumn<OfficePayment, String> createdAtColumn = new TableColumn<>("الإنشاء");
        createdAtColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCreatedAt().toString()));

        TableColumn<OfficePayment, String> updatedAtColumn = new TableColumn<>("التعديل");
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
        AddOfficePayment.formType = FormType.CREATE;
        AddOfficePayment.controller = this;
        Modal.start(this.getClass(), "addOfficePayment.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.controller = this;
        DeleteConfirmation.deleteUrl = service.getEndPoint();
        DeleteConfirmation.selected = selectedOfficePayment.getId();
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddOfficePayment.formType = FormType.UPDATE;
        AddOfficePayment.controller = this;
        Modal.start(this.getClass(), "addOfficePayment.fxml");
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
        AddOfficePayment.formType = FormType.GET;
        AddOfficePayment.controller = this;
        Modal.start(this.getClass(), "addOfficePayment.fxml");
    }

    @Override
    public void removeData() {
        observableList.remove(selectedOfficePayment);
        loadData();
    }

    @Override
    public void addData(Object object) {
        observableList.add((OfficePayment) object);
        loadData();
    }

    @Override
    public void loadData() {
        final List<OfficePayment> items;
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
