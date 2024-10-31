package com.example.desktop.sellerPayment;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.sellerPayment.SellerPaymentService;
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
import org.example.model.OfficePayment;
import org.example.model.SellerPayment;

import java.util.List;
import java.util.Objects;

public class SellerPayments implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<SellerPayment> tableTbl;

    private static ObservableList<SellerPayment> observableList;
    private String query = null;
    private final SellerPaymentService service = SellerPaymentService.getInstance();
    public static SellerPayment selectedSellerPayment;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SellerPayment>() {
            @Override
            public void changed(ObservableValue<? extends SellerPayment> observableValue, SellerPayment officePayment, SellerPayment t1) {
                selectedSellerPayment = t1;
            }
        });
    }

    private void setTable() {
        TableColumn<SellerPayment, String> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId().toString()));

        TableColumn<SellerPayment, String> amountColumn = new TableColumn<>("المبلغ");
        amountColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAmount().getAmount()+" "+data.getValue().getAmount().getCurrency()));

        TableColumn<SellerPayment, String> billNumberColumn = new TableColumn<>("رقم الايصال");
        billNumberColumn.setCellValueFactory(data -> {
            if(Objects.nonNull(data.getValue().getBillNumber()))
                return new SimpleStringProperty(data.getValue().getBillNumber().toString());
            else return new SimpleStringProperty("");
        });

        TableColumn<SellerPayment, String> notesColumn = new TableColumn<>("ملاحظات");
        notesColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNotes()));

        TableColumn<SellerPayment, String> gasStationColumn = new TableColumn<>("Seller");
        gasStationColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSeller().getName()));

        TableColumn<SellerPayment, String> createdAtColumn = new TableColumn<>("الإنشاء");
        createdAtColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCreatedAt().toString()));

        TableColumn<SellerPayment, String> updatedAtColumn = new TableColumn<>("التعديل");
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
        AddSellerPayment.controller = this;
        AddSellerPayment.formType = FormType.CREATE;
        Modal.start(this.getClass(),"addSellerPayment.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.controller = this;
        DeleteConfirmation.deleteUrl = service.getEndPoint();
        DeleteConfirmation.selected = selectedSellerPayment.getId();
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddSellerPayment.controller = this;
        AddSellerPayment.formType = FormType.UPDATE;
        Modal.start(this.getClass(),"addSellerPayment.fxml");
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
        AddSellerPayment.controller = this;
        AddSellerPayment.formType = FormType.GET;
        Modal.start(this.getClass(),"addSellerPayment.fxml");
    }

    @Override
    public void removeData() {
        observableList.remove(selectedSellerPayment);
        loadData();
    }

    @Override
    public void addData(Object object) {
        observableList.add((SellerPayment) object);
        loadData();
    }

    @Override
    public void loadData() {
        final List<SellerPayment> items;
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
