package com.example.desktop.paidToParent;

import com.example.desktop.delete.DeleteConfirmation;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.paidToParent.PaidToParentService;
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
import org.example.model.PaidToParent;

import java.util.List;
import java.util.Objects;

public class PaidToParents implements TableController {

    private static ObservableList<PaidToParent> observableList;
    private final PaidToParentService service = PaidToParentService.getInstance();
    private String query = null;
    public static PaidToParent selectedPaidToParent;
    @FXML
    private TextField page;

    @FXML
    private TableView<PaidToParent> tableTbl;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PaidToParent>() {
            @Override
            public void changed(ObservableValue<? extends PaidToParent> observableValue, PaidToParent paidToParent, PaidToParent t1) {
                selectedPaidToParent = t1;
            }
        });
    }

    private void setTable() {
        TableColumn< PaidToParent, String> idColumn = new TableColumn<>("id");
        idColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId().toString()));

        TableColumn<PaidToParent, String> amountColumn = new TableColumn<>("المبلغ");
        amountColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAmount().getAmount()+" "+data.getValue().getAmount().getCurrency()));

        TableColumn<PaidToParent, String> billNumberColumn = new TableColumn<>("رقم الايصال");
        billNumberColumn.setCellValueFactory(data -> {
            if(Objects.nonNull(data.getValue().getBillNumber()))
                return new SimpleStringProperty(data.getValue().getBillNumber().toString());
            else return new SimpleStringProperty("");
        });

        TableColumn<PaidToParent, String> notesColumn = new TableColumn<>("ملاحظات");
        notesColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNotes()));

        TableColumn<PaidToParent, String> createdAtColumn = new TableColumn<>("الإنشاء");
        createdAtColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCreatedAt().toString()));

        TableColumn<PaidToParent, String> updatedAtColumn = new TableColumn<>("التعديل");
        updatedAtColumn.setCellValueFactory(data ->
                {
                    if(Objects.nonNull(data.getValue().getUpdatedAt()))
                        return new SimpleStringProperty(data.getValue().getUpdatedAt().toString());
                    else return new SimpleStringProperty("");
                }
        );

        tableTbl.getColumns().addAll(idColumn, amountColumn, billNumberColumn, notesColumn, createdAtColumn, updatedAtColumn);
        tableTbl.setItems(observableList);
    }

    @FXML
    void add() {
        AddPaidToParent.formType = FormType.CREATE;
        AddPaidToParent.controller = this;
        Modal.start(this.getClass(), "addPaidToParent.fxml");
    }

    @FXML
    void delete() {
        DeleteConfirmation.controller = this;
        DeleteConfirmation.deleteUrl = service.getEndPoint();
        DeleteConfirmation.selected = selectedPaidToParent.getId();
        Modal.start(this.getClass(), "/com/example/desktop/delete/deleteConfirmation.fxml");
    }

    @FXML
    void edit() {
        AddPaidToParent.formType = FormType.UPDATE;
        AddPaidToParent.controller = this;
        Modal.start(this.getClass(), "addPaidToParent.fxml");
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
        AddPaidToParent.formType = FormType.GET;
        AddPaidToParent.controller = this;
        Modal.start(this.getClass(), "addPaidToParent.fxml");
    }

    @Override
    public void removeData() {
        observableList.remove(selectedPaidToParent);
        loadData();
    }

    @Override
    public void addData(Object object) {
        observableList.add((PaidToParent) object);
        loadData();
    }

    @Override
    public void loadData() {
        final List<PaidToParent> items;
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
