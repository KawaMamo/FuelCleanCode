package com.example.desktop.transportation;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.tools.FormType;
import com.example.model.transportation.TransportationService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.Notifications;
import org.example.model.Forfeit;
import org.example.model.Partition;
import org.example.model.Transportation;

import java.text.NumberFormat;
import java.util.List;
import java.util.Objects;

public class Transportations implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<Transportation> tableTbl;
    private static ObservableList<Transportation> observableList;
    public static Transportation selectedTransportation;
    public static Forfeit selectedForfeit;
    public static Partition selectedPartition;
    private final TransportationService transportationService = TransportationService.getInstance();
    private String query = null;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, Transportation, t1) -> selectedTransportation = t1);
    }

    private void setTable() {

        TableColumn<Transportation, String> idCol = new TableColumn<>("id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Transportation, String> vehicleCol = new TableColumn<>("vehicle");
        vehicleCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVehicle().getPlateNumber()+" "+
                data.getValue().getVehicle().getDriver().getName()));

        TableColumn<Transportation, String> refineryCol = new TableColumn<>("refinery");
        refineryCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRefinery().getName()));

        TableColumn<Transportation, String> sizeCol = new TableColumn<>("size");
        sizeCol.setCellValueFactory(data -> new SimpleStringProperty(NumberFormat.getInstance().format(data.getValue().getSize())));

        TableColumn<Transportation, String> partitionsCol = new TableColumn<>("partitions");
        partitionsCol.setCellValueFactory(
                data -> {
                    StringBuilder partitionString = new StringBuilder();
                    if(Objects.nonNull(data.getValue().getPartitions())){
                        data.getValue().getPartitions().forEach(partition -> partitionString.append(partition.getGasStation().getName()).append(" "));
                        return new SimpleStringProperty(partitionString.toString());
                    }else return new SimpleStringProperty("NA");
                }
        );

        TableColumn<Transportation,String> dateCol = new TableColumn<>("date");
        dateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCreatedAt().toString()));

        tableTbl.getColumns().addAll(idCol, vehicleCol, refineryCol, sizeCol, partitionsCol, dateCol);
        tableTbl.setItems(observableList);
    }

    @FXML
    void add() {
        AddTransportation.controller = this;
        AddTransportation.formType = FormType.CREATE;
        Modal.start(this.getClass(), "addTransportation.fxml");
    }

    @FXML
    void delete() {
        final Transportation delete = transportationService.delete(selectedTransportation.getId());
        Notifications.create().title("Success").text("Successfully deleted "+delete.getId()).showInformation();
        loadData();
    }

    @FXML
    void edit() {
        AddTransportation.controller = this;
        AddTransportation.formType = FormType.UPDATE;
        Modal.start(this.getClass(), "addTransportation.fxml");
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
        AddTransportation.controller = this;
        AddTransportation.formType = FormType.GET;
        Modal.start(this.getClass(), "addTransportation.fxml");
    }

    @Override
    public void removeData() {
        observableList.remove(selectedTransportation);
        loadData();
    }

    @Override
    public void addData(Object object) {
        observableList.add((Transportation) object);
        loadData();
    }

    @Override
    public void loadData() {
        final List<Transportation> items;
        if(Objects.isNull(query))
            items = transportationService.getItems(Integer.parseInt(page.getText()) - 1, 15);
        else items = transportationService.getItems(Integer.parseInt(page.getText()) - 1, 15, query);

        if(Objects.nonNull(items))
            observableList = FXCollections.observableList(items);
        tableTbl.setItems(observableList);
    }

    @Override
    public void setQuery(String query) {
        this.query = query;
    }
}
