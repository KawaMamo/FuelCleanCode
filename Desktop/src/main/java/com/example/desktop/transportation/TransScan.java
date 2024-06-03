package com.example.desktop.transportation;

import com.example.model.TableController;
import com.example.model.client.Client;
import com.example.model.transportation.TransportationService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.example.model.Document;
import org.example.model.Transportation;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class TransScan implements TableController {

    @FXML
    private TableView<Document> fileTBL;

    @FXML
    private TextField page;

    @FXML
    private TableView<Transportation> tableTbl;
    private static ObservableList<Transportation> observableList;
    public static ObservableList<Document> documentObservableList;
    public static Transportation selectedTransportation;
    public static Document selectedDocument;
    private final TransportationService transportationService = TransportationService.getInstance();
    private String query = null;

    @FXML
    private void initialize(){
        loadData();
        setTable();
        setDocumentTable();
        tableTbl.getSelectionModel().selectedItemProperty().addListener((observableValue, Transportation, t1) -> {
            selectedTransportation = t1;
            loadDocuments();
        });

        fileTBL.getSelectionModel().selectedItemProperty().addListener((observableValue, document, t1) -> selectedDocument = t1);

    }

    @Override
    public void removeData() {

    }

    @Override
    public void addData(Object object) {

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

        tableTbl.getColumns().addAll(idCol, vehicleCol, refineryCol, sizeCol, partitionsCol);
        tableTbl.setItems(observableList);
    }

    private void setDocumentTable(){
        TableColumn<Document, Long> urlCol = new TableColumn<>("Name");
        urlCol.setCellValueFactory(new PropertyValueFactory<>("url"));

        fileTBL.getColumns().add(urlCol);

        fileTBL.setItems(documentObservableList);
    }

    private void loadDocuments(){
        final List<Document> documents = new ArrayList<>();

        for (Transportation transportation : observableList) {
            if(Objects.equals(transportation.getId(), selectedTransportation.getId()) && Objects.nonNull(transportation.getDocument()))
                documents.addAll(transportation.getDocument());
        }
        documentObservableList = FXCollections.observableList(documents);
        fileTBL.setItems(documentObservableList);
    }

    @Override
    public void setQuery(String query) {

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

    @FXML
    void upload() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterPNG, extFilterpng);
        File file = fileChooser.showOpenDialog(null);
        String request = "{\"fileName\": \""+file.getName()+"\",\n \"type\": \"png\"\n}";
        final Transportation transportation = transportationService.uploadImage(selectedTransportation.getId(), file.getPath(), request);
        loadDocuments();
    }

    @FXML
    void preview() {
        final byte[] bytes = selectedDocument.getContent().getBytes(StandardCharsets.UTF_8);
        final File file = new File("testFile.png");
        try (final FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            final Base64.Decoder decoder = Base64.getDecoder();
            fileOutputStream.write(decoder.decode(bytes));
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+file.getPath());
            System.out.println(file.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
