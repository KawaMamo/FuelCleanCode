package com.example.desktop.gasStation;

import com.example.model.clientPayment.ClientPaymentService;
import com.example.model.gasStation.GasStationService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;
import org.example.model.GasStation;
import org.example.model.Money;

import java.text.NumberFormat;
import java.util.List;

public class GasStationDetails {

    @FXML
    private TextField gasStationTF;

    @FXML
    private Label paymentsSP;

    @FXML
    private Label paymentsUSD;

    @FXML
    private Label receivedSP;

    @FXML
    private Label receivedUSD;

    @FXML
    private Label returnedSP;

    @FXML
    private Label returnedUSD;

    @FXML
    private Label transferSP;

    @FXML
    private Label transferUSD;

    private final GasStationService gasStationService = GasStationService.getInstance();
    private final ClientPaymentService clientPaymentService = ClientPaymentService.getInstance();
    private static GasStation selectedGasStation;

    @FXML
    void initialize(){
        final List<GasStation> gasStations = gasStationService.getItems(null, null);
        final List<String> stationList = gasStations.stream().map(GasStation::getTranslation).toList();
        TextFields.bindAutoCompletion(gasStationTF, stationList);

        gasStationTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(gasStationTF.getText().length()>0){
                    selectedGasStation = gasStations.get(stationList.indexOf(gasStationTF.getText()));
                }
            }
        });
    }

    @FXML
    void details() {
        final List<Money> totalPaymentsForClient = clientPaymentService.getTotalPaymentsForClient(selectedGasStation.getId());
        for (Money money : totalPaymentsForClient) {
            if(money.getCurrency().equals("SP"))
                paymentsSP.setText(NumberFormat.getInstance().format(money.getAmount()));
            if(money.getCurrency().equals("USD"))
                paymentsUSD.setText(NumberFormat.getInstance().format(money.getAmount()));
        }
    }

    @FXML
    void paymentsDetails() {

    }

    @FXML
    void receivedDetails() {

    }

    @FXML
    void returnedDetails() {

    }

    @FXML
    void transferredDetails() {

    }
}
