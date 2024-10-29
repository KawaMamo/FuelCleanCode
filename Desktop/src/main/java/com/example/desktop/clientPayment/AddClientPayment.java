package com.example.desktop.clientPayment;

import com.example.model.TableController;
import com.example.model.clientPayment.ClientPaymentService;
import com.example.model.gasStation.GasStationService;
import com.example.model.modal.Modal;
import com.example.model.tools.FormType;
import com.example.model.tools.QueryBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import org.example.contract.request.create.CreateClientPaymentRequest;
import org.example.contract.request.update.UpdateClientPaymentRequest;
import org.example.model.ClientPayment;
import org.example.model.GasStation;
import org.example.model.Money;

import java.util.List;
import java.util.Objects;

public class AddClientPayment {


    @FXML
    private TextField amountTF;

    @FXML
    private TextField billNumberTF;

    @FXML
    private ChoiceBox<String> currencyCB;

    @FXML
    private TextField gasStationTF;

    @FXML
    private TextField notesTF;

    @FXML
    private Button submitBtn;

    public static TableController controller;
    public static FormType formType = FormType.CREATE;
    private final ClientPaymentService service = ClientPaymentService.getInstance();

    private Long selectedGasStationId;
    private final GasStationService gasStationService = GasStationService.getInstance();

    @FXML
    private void initialize(){

        currencyCB.getItems().addAll("USD", "SP");
        if(formType.equals(FormType.UPDATE)){
            final ClientPayment item = service.getItem(ClientPayments.selectedClientPayment.getId());
            gasStationTF.setText(item.getGasStation().getName());
            if(Objects.nonNull(item.getBillNumber()))
                billNumberTF.setText(item.getBillNumber().toString());
            amountTF.setText(item.getAmount().getAmount().toString());
            currencyCB.setValue(item.getAmount().getCurrency());
            notesTF.setText(item.getNotes());
            selectedGasStationId = item.getGasStation().getId();
        }

        final List<GasStation> gasStationList = gasStationService.getItems(null, null);
        final List<String> gasStationNames = gasStationList.stream().map(GasStation::getName).toList();
        TextFields.bindAutoCompletion(gasStationTF, gasStationNames);
        gasStationTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(gasStationTF.getText().length()>0){
                    if(gasStationNames.contains(gasStationTF.getText())) {
                        final GasStation gasStation = gasStationList.get(gasStationNames.indexOf(gasStationTF.getText()));
                        selectedGasStationId = gasStation.getId();
                    }
                }
            }
        });
    }

    @FXML
    void submit() {
        final ClientPayment clientPayment;
        if(formType.equals(FormType.UPDATE)){
            clientPayment = service.editItem(new UpdateClientPaymentRequest(ClientPayments.selectedClientPayment.getId(),
                    new Money(currencyCB.getValue(),
                            Double.parseDouble(amountTF.getText())),
                    Long.parseLong(billNumberTF.getText()),
                    notesTF.getText(),
                    selectedGasStationId));
        }else if(formType.equals(FormType.CREATE)){
            clientPayment = service.addItem(new CreateClientPaymentRequest(new Money(currencyCB.getValue(),
                    Double.parseDouble(amountTF.getText())),
                    Long.parseLong(billNumberTF.getText()),
                    notesTF.getText(),
                    selectedGasStationId));
        }else {
            clientPayment = new ClientPayment();
            final QueryBuilder queryBuilder = new QueryBuilder();
            if(Objects.nonNull(selectedGasStationId))
                queryBuilder.addQueryParameter("gasStation", selectedGasStationId.toString());
            if(billNumberTF.getText().length()>1)
                queryBuilder.addQueryParameter("billNumber", billNumberTF.getText());
            if(amountTF.getText().length()>0)
                queryBuilder.addQueryParameter("priceAmount", amountTF.getText());
            if(Objects.nonNull(currencyCB.getValue()))
                queryBuilder.addQueryParameter("priceCurrency", currencyCB.getValue());
            if(notesTF.getText().length()>0)
                queryBuilder.addQueryParameter("notes", notesTF.getText());

            queryBuilder.sort("id", "desc");
            controller.setQuery(queryBuilder.getQuery());
        }
        notify(clientPayment);
    }

    private static void notify(ClientPayment clientPayment) {
        String message;
        if(Objects.nonNull(clientPayment.getId())){
            message = "clientPayment added";
        }else {
            message = "something went wrong";
        }
        controller.addData(clientPayment);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
