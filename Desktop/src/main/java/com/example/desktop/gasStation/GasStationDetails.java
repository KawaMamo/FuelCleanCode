package com.example.desktop.gasStation;

import com.example.model.clientPayment.ClientPaymentService;
import com.example.model.gasStation.GasStationService;
import com.example.model.partition.PartitionService;
import com.example.model.returnedMaterial.ReturnedMaterialService;
import com.example.model.transferMaterial.TransferMaterialsService;
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
    private final PartitionService partitionService = PartitionService.getINSTANCE();
    private final ReturnedMaterialService returnedMaterialService = ReturnedMaterialService.getInstance();
    private final TransferMaterialsService transferMaterialsService = TransferMaterialsService.getInstance();
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
        transferSP.setText("0.0");
        returnedSP.setText("0.0");
        receivedSP.setText("0.0");
        paymentsSP.setText("0.0");

        transferUSD.setText("0.0");
        returnedUSD.setText("0.0");
        receivedUSD.setText("0.0");
        paymentsUSD.setText("0.0");

        final List<Money> totalPaymentsForClient = clientPaymentService.getTotalPaymentsForClient(selectedGasStation.getId());
        for (Money money : totalPaymentsForClient) {
            if(money.getCurrency().equals("SP"))
                paymentsSP.setText(NumberFormat.getInstance().format(money.getAmount()));
            if(money.getCurrency().equals("USD"))
                paymentsUSD.setText(NumberFormat.getInstance().format(money.getAmount()));
        }

        final List<Money> totalReceivedMaterials = partitionService.getTotalReceivedMaterials(selectedGasStation.getId());
        for (Money money : totalReceivedMaterials) {
            if(money.getCurrency().equals("SP"))
                receivedSP.setText(NumberFormat.getInstance().format(money.getAmount()));
            if(money.getCurrency().equals("USD"))
                receivedUSD.setText(NumberFormat.getInstance().format(money.getAmount()));
        }

        final List<Money> totalReturnedMaterials = returnedMaterialService.getTotalReturnedMaterials(selectedGasStation.getId());
        for (Money money : totalReturnedMaterials) {
            if(money.getCurrency().equals("SP"))
                returnedSP.setText(NumberFormat.getInstance().format(money.getAmount()));
            if(money.getCurrency().equals("USD"))
                returnedUSD.setText(NumberFormat.getInstance().format(money.getAmount()));
        }

        final List<Money> totalTransfersTo = transferMaterialsService.getTotalTransfersTo(selectedGasStation.getId());
        final List<Money> totalTransfersFrom = transferMaterialsService.getTotalTransfersFrom(selectedGasStation.getId());
        Double transferSPValue = 0.0;
        Double transferUSDValue = 0.0;
        for (Money money : totalTransfersTo) {
            if(money.getCurrency().equals("SP"))
                transferSPValue = money.getAmount();
            if(money.getCurrency().equals("USD"))
                transferUSDValue = money.getAmount();
        }

        for (Money money : totalTransfersFrom) {
            if(money.getCurrency().equals("SP"))
                transferSPValue -= money.getAmount();
            if(money.getCurrency().equals("USD"))
                transferUSDValue -= money.getAmount();
        }

        transferSP.setText(NumberFormat.getInstance().format(transferSPValue));
        transferUSD.setText(NumberFormat.getInstance().format(transferUSDValue));
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
