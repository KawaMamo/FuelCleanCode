package com.example.desktop.transLine;

import com.example.model.TableController;
import com.example.model.transLine.TransLineService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.GasStation;
import org.example.model.Place;
import org.example.model.Refinery;
import org.example.model.TransLine;

import java.text.NumberFormat;

public class TransLines implements TableController {

    @FXML
    private TextField page;

    @FXML
    private TableView<TransLine> tableTbl;
    private ObservableList<TransLine> transLines;
    public static TransLine selectedTransLine;

    private final TransLineService transLineService = TransLineService.getInstance();

    @FXML
    private void initialize(){
        loadData();
        setTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, GasStation, t1) -> selectedTransLine = t1);
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
        transLines = FXCollections.observableArrayList(transLineService.getItems(null, null));
    }

    private void setTable() {
        TableColumn<TransLine, String> idCol = new TableColumn<>("التسلسل");
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        TableColumn<TransLine, String> sourceCol = new TableColumn<>("المصدر");
        sourceCol.setCellValueFactory(
                data-> {
                    final Place source = data.getValue().getSource();
                    if(source instanceof Refinery) {
                        return new SimpleStringProperty(((Refinery) source).getName());
                    }
                    else if (source instanceof GasStation)
                        return new SimpleStringProperty(((GasStation) source).getName());
                    else return null;
                });

        TableColumn<TransLine, String> destinationCol = new TableColumn<>("الوجهة");
        destinationCol.setCellValueFactory(data -> {
            final Place source = data.getValue().getDestination();
            if(source instanceof Refinery) {
                return new SimpleStringProperty(((Refinery) source).getName());
            }
            else if (source instanceof GasStation)
                return new SimpleStringProperty(((GasStation) source).getName());
            else return null;
        });

        TableColumn<TransLine, String> feeCol = new TableColumn<>("الكلفة");
        feeCol.setCellValueFactory(
                data-> new SimpleStringProperty(
                        NumberFormat.getInstance().format(data.getValue().getFee().getAmount())+" "+data.getValue().getFee().getCurrency()));

        TableColumn<TransLine, String> createdAtCol = new TableColumn<>("تاريخ الإنشاء");
        createdAtCol.setCellValueFactory(
                new PropertyValueFactory<>("createdAt"));

        TableColumn<TransLine, String> updatedAtCol = new TableColumn<>("تاريخ التعديل");
        updatedAtCol.setCellValueFactory(
                new PropertyValueFactory<>("updatedAt"));

        tableTbl.getColumns().addAll(idCol, sourceCol, destinationCol, feeCol, createdAtCol, updatedAtCol);
        tableTbl.setItems(transLines);
    }
}
