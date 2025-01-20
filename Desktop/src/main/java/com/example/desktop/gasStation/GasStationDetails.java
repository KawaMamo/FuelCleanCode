package com.example.desktop.gasStation;

import com.example.desktop.HelloApplication;
import com.example.desktop.reports.DriverReport;
import com.example.model.clientPayment.ClientPaymentService;
import com.example.model.gasStation.GasStationService;
import com.example.model.partition.PartitionService;
import com.example.model.returnedMaterial.ReturnedMaterialService;
import com.example.model.transferMaterial.TransferMaterialsService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import org.example.model.GasStation;
import org.example.model.Money;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

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
    @FXML
    private ToggleButton commercialTB;

    @FXML
    private DatePicker endDP;
    @FXML
    private ToggleButton normalTB;
    @FXML
    private DatePicker startDP;
    final ToggleGroup transTypeGroup = new ToggleGroup();

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

        normalTB.setToggleGroup(transTypeGroup);
        commercialTB.setToggleGroup(transTypeGroup);
        normalTB.setSelected(true);
        normalTB.setId("normal");
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
        if(Objects.isNull(selectedGasStation) || Objects.isNull(startDP.getValue()) || Objects.isNull(endDP.getValue())){
            Notifications.create().text("يرجى اختيار عنصر وتحديد تاريخي البدء ةالانتهاء").title("chosse something").showInformation();
        }else {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"));
            final File file = fileChooser.showSaveDialog(HelloApplication.primaryStage);
            String transType = normalTB.isSelected() ? "NORMAL":"COMMERCIAL";
            final byte[] bytes = clientPaymentService.getPaymentsReport(file.getName().split("\\.")[1].toUpperCase(),
                    startDP.getValue(),
                    endDP.getValue(),
                    selectedGasStation.getId());

            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(Base64.getDecoder().decode(bytes));
                if(!List.of(Objects.requireNonNull(file.getParentFile().list())).contains("regionReport.html_files")){

                    final InputStream resourceAsStream = DriverReport.class.getClassLoader().getResourceAsStream("wave.png");
                    final InputStream logoStream = DriverReport.class.getClassLoader().getResourceAsStream("SadLogo.png");

                    final File outputFile = new File(file.getParentFile() + "/regionReport.html_files/img_0_0_2.png");
                    final File logOutput = new File(file.getParentFile() + "/regionReport.html_files/img_0_0_0.png");
                    outputFile.getParentFile().mkdir();
                    outputFile.createNewFile();
                    logOutput.createNewFile();
                    final FileOutputStream waveOutputStream = new FileOutputStream(outputFile);
                    final FileOutputStream logoOutPutStream = new FileOutputStream(logOutput);
                    int info = 0;
                    while( (info = resourceAsStream.read()) != -1) {
                        waveOutputStream.write(info);
                    }
                    int info2 = 0;
                    while ((info2 = logoStream.read()) != -1){
                        logoOutPutStream.write(info2);
                    }
                    waveOutputStream.close();
                    resourceAsStream.close();
                    logoStream.close();
                    logoOutPutStream.close();
                }
                Runtime.getRuntime().exec("rundll32.exe shell32.dll ShellExec_RunDLL " +file.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void receivedDetails() {
        if(Objects.isNull(selectedGasStation) || Objects.isNull(startDP.getValue()) || Objects.isNull(endDP.getValue())){
            Notifications.create().text("يرجى اختيار عنصر وتحديد تاريخي البدء ةالانتهاء").title("chosse something").showInformation();
        }else {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"));
            final File file = fileChooser.showSaveDialog(HelloApplication.primaryStage);
            String transType = normalTB.isSelected() ? "NORMAL":"COMMERCIAL";
            final byte[] bytes = partitionService.getReceivedMaterialsReport(file.getName().split("\\.")[1].toUpperCase(),
                    transType,
                    startDP.getValue(),
                    endDP.getValue(),
                    selectedGasStation.getId());

            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(Base64.getDecoder().decode(bytes));
                if(!List.of(Objects.requireNonNull(file.getParentFile().list())).contains("DriverReport.html_files")){

                    final InputStream resourceAsStream = DriverReport.class.getClassLoader().getResourceAsStream("wave.png");
                    final InputStream logoStream = DriverReport.class.getClassLoader().getResourceAsStream("SadLogo.png");

                    final File outputFile = new File(file.getParentFile() + "/DriverReport.html_files/img_0_0_2.png");
                    final File logOutput = new File(file.getParentFile() + "/DriverReport.html_files/img_0_0_0.png");
                    outputFile.getParentFile().mkdir();
                    outputFile.createNewFile();
                    logOutput.createNewFile();
                    final FileOutputStream waveOutputStream = new FileOutputStream(outputFile);
                    final FileOutputStream logoOutPutStream = new FileOutputStream(logOutput);
                    int info = 0;
                    while( (info = resourceAsStream.read()) != -1) {
                        waveOutputStream.write(info);
                    }
                    int info2 = 0;
                    while ((info2 = logoStream.read()) != -1){
                        logoOutPutStream.write(info2);
                    }
                    waveOutputStream.close();
                    resourceAsStream.close();
                    logoStream.close();
                    logoOutPutStream.close();
                }
                Runtime.getRuntime().exec("rundll32.exe shell32.dll ShellExec_RunDLL " +file.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void returnedDetails() {
        if(Objects.isNull(selectedGasStation) || Objects.isNull(startDP.getValue()) || Objects.isNull(endDP.getValue())){
            Notifications.create().text("يرجى اختيار عنصر وتحديد تاريخي البدء ةالانتهاء").title("chosse something").showInformation();
        }else {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"));
            final File file = fileChooser.showSaveDialog(HelloApplication.primaryStage);
            String transType = normalTB.isSelected() ? "NORMAL":"COMMERCIAL";
            final byte[] bytes = returnedMaterialService.getReturnedMaterialsReport(file.getName().split("\\.")[1].toUpperCase(),
                    startDP.getValue(),
                    endDP.getValue(),
                    selectedGasStation.getId());

            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(Base64.getDecoder().decode(bytes));
                if(!List.of(Objects.requireNonNull(file.getParentFile().list())).contains("regionReport.html_files")){

                    final InputStream resourceAsStream = DriverReport.class.getClassLoader().getResourceAsStream("wave.png");
                    final InputStream logoStream = DriverReport.class.getClassLoader().getResourceAsStream("SadLogo.png");

                    final File outputFile = new File(file.getParentFile() + "/regionReport.html_files/img_0_0_2.png");
                    final File logOutput = new File(file.getParentFile() + "/regionReport.html_files/img_0_0_0.png");
                    outputFile.getParentFile().mkdir();
                    outputFile.createNewFile();
                    logOutput.createNewFile();
                    final FileOutputStream waveOutputStream = new FileOutputStream(outputFile);
                    final FileOutputStream logoOutPutStream = new FileOutputStream(logOutput);
                    int info = 0;
                    while( (info = resourceAsStream.read()) != -1) {
                        waveOutputStream.write(info);
                    }
                    int info2 = 0;
                    while ((info2 = logoStream.read()) != -1){
                        logoOutPutStream.write(info2);
                    }
                    waveOutputStream.close();
                    resourceAsStream.close();
                    logoStream.close();
                    logoOutPutStream.close();
                }
                Runtime.getRuntime().exec("rundll32.exe shell32.dll ShellExec_RunDLL " +file.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void transferredDetails() {

    }

    @FXML
    void report() {


    }
}
