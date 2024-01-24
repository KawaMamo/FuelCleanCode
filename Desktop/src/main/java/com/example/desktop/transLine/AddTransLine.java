package com.example.desktop.transLine;

import com.example.model.TableController;
import com.example.model.gasStation.GasStationService;
import com.example.model.modal.Modal;
import com.example.model.refinery.RefineryService;
import com.example.model.region.RegionService;
import com.example.model.tools.FormType;
import com.example.model.tools.QueryBuilder;
import com.example.model.transLine.TransLineService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import org.example.contract.request.create.CreateTransLineRequest;
import org.example.contract.request.update.UpdateTransLineRequest;
import org.example.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddTransLine {
    public static TableController controller;
    public static FormType formType = FormType.CREATE;

    @FXML
    private TextField amountTF;

    @FXML
    private ChoiceBox<String> currencyCB;

    @FXML
    private TextField destinationTF;

    @FXML
    private TextField sourceTF;

    @FXML
    private Button submitBtn;

    private final GasStationService gasStationService = GasStationService.getInstance();
    private final RefineryService refineryService = RefineryService.getInstance();
    private final RegionService regionService = RegionService.getInstance();
    private final TransLineService transLineService = TransLineService.getInstance();

    private Long selectedSourceId;
    private Long selectedDestinationId;

    @FXML
    private void initialize(){
        currencyCB.getItems().addAll("USD", "SP");

        List<Place> places = new ArrayList<Place>();
        final List<Refinery> refineries = refineryService.getItems(null, null);
        final List<GasStation> stations = gasStationService.getItems(null, null);
        final List<Region> regions = regionService.getItems(null, null);

        List<String> placeNames = new ArrayList<String>();
        if(Objects.nonNull(refineries) && Objects.nonNull(stations) && Objects.nonNull(regions)) {

            places.addAll(refineries);
            places.addAll(stations);
            places.addAll(regions);

            placeNames.addAll(places.stream().map(Place::getName).toList());
            TextFields.bindAutoCompletion(sourceTF, placeNames);
            TextFields.bindAutoCompletion(destinationTF, placeNames);
        }

        sourceTF.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(sourceTF.getText().length()>0){
                if(placeNames.contains(sourceTF.getText())){
                    final Place place = places.get(placeNames.indexOf(sourceTF.getText()));
                    selectedSourceId = place.getId();
                }

            }
        });

        destinationTF.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(destinationTF.getText().length()>0){
                if(placeNames.contains(destinationTF.getText())){
                    final Place place = places.get(placeNames.indexOf(destinationTF.getText()));
                    selectedDestinationId = place.getId();
                }
            }
        });

        if(formType.equals(FormType.UPDATE)){
            final TransLine transLine = transLineService.getItem(TransLines.selectedTransLine.getId());
            sourceTF.setText(transLine.getSource().getName());
            destinationTF.setText(transLine.getDestination().getName());
            amountTF.setText(String.valueOf(transLine.getFee().getAmount()));
            currencyCB.setValue(transLine.getFee().getCurrency());
            selectedSourceId = transLine.getSource().getId();
            selectedDestinationId = transLine.getDestination().getId();
        }

    }
    @FXML
    void submit() {
        final TransLine transLine;
        if(formType.equals(FormType.UPDATE)){
            transLine = transLineService.editItem(new UpdateTransLineRequest(TransLines.selectedTransLine.getId(),
                    selectedSourceId,
                    selectedDestinationId,
                    currencyCB.getValue(),
                    Double.parseDouble(amountTF.getText())));
        }else if(formType.equals(FormType.CREATE)){
            transLine = transLineService.addItem(new CreateTransLineRequest(selectedSourceId,
                    selectedDestinationId,
                    currencyCB.getValue(),
                    Double.parseDouble(amountTF.getText())));
        }else {
            transLine = new TransLine();
            QueryBuilder queryBuilder = new QueryBuilder();
            if(Objects.nonNull(selectedSourceId))
                queryBuilder.addQueryParameter("source", selectedSourceId.toString());
            if(Objects.nonNull(selectedDestinationId))
                queryBuilder.addQueryParameter("destination", selectedDestinationId.toString());
            queryBuilder.sort();
            controller.setQuery(queryBuilder.getQuery());
        }

        notify(transLine);
    }

    private static void notify(TransLine transLine) {
        String message;
        if(Objects.nonNull(transLine.getId())){
            message = "Vehicle added";
        }else {
            message = "something went wrong";
        }
        controller.addData(transLine);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }


}
