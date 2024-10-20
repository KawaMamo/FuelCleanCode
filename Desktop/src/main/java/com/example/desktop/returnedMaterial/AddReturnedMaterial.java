package com.example.desktop.returnedMaterial;

import com.example.model.TableController;
import com.example.model.buyer.BuyerService;
import com.example.model.gasStation.GasStationService;
import com.example.model.material.MaterialService;
import com.example.model.modal.Modal;
import com.example.model.returnedMaterial.ReturnedMaterialService;
import com.example.model.tools.FormType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import org.example.model.*;

import java.util.List;
import java.util.Objects;


public class AddReturnedMaterial {

    public static TableController controller;
    public static FormType formType = FormType.CREATE;
    private final GasStationService gasStationService = GasStationService.getInstance();
    private final MaterialService materialService = MaterialService.getInstance();
    private final ReturnedMaterialService returnedMaterialService = ReturnedMaterialService.getInstance();
    private final BuyerService buyerService = BuyerService.getInstance();

    @FXML
    private TextField amountTF;

    @FXML
    private TextField buyerTF;

    @FXML
    private TextField centerPriceTF;

    @FXML
    private ChoiceBox<String> currencyCB;

    @FXML
    private TextField materialTF;

    @FXML
    private TextField priceTF;

    @FXML
    private TextField stationTF;

    @FXML
    private Button submitBtn;
    private Long selectedStationId;
    private Long selectedMaterialId;
    private Long selectedBuyerId;

    @FXML
    private void initialize(){
        currencyCB.getItems().add("USD");
        currencyCB.getItems().add("SP");
        if(formType.equals(FormType.UPDATE)){
            final ReturnedMaterial item = returnedMaterialService.getItem(ReturnedMaterials.selectedReturnedMaterial.getId());
            stationTF.setText(item.getGasStation().getName());
            materialTF.setText(item.getMaterial().getName());
            priceTF.setText(item.getPrice().getAmount()+" "+item.getPrice().getCurrency());
            centerPriceTF.setText(item.getCenterPrice().getAmount().toString());
            currencyCB.setValue(item.getCenterPrice().getCurrency());
            buyerTF.setText(item.getBuyer().getName()+" "+item.getBuyer().getOrganization());
            selectedMaterialId = item.getMaterial().getId();
            selectedBuyerId = item.getBuyer().getId();
            selectedStationId = item.getGasStation().getId();
        }

        final List<GasStation> gasStations = gasStationService.getItems(null, null);
        final List<Material> materials = materialService.getItems(null, null);

        final List<String> materialList = materials.stream().map(Material::getName).toList();
        final List<String> gasStationList = gasStations.stream().map(GasStation::getName).toList();
        final List<Buyer> buyerList = buyerService.getItems(null, null);
        final List<String> buyers = buyerList.stream().map(Buyer::getName).toList();

        TextFields.bindAutoCompletion(stationTF, gasStationList);
        TextFields.bindAutoCompletion(materialTF, materialList);
        TextFields.bindAutoCompletion(buyerTF, buyers);

        stationTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(stationTF.getText().length()>0){
                    if(gasStationList.contains(stationTF.getText())){
                        final GasStation gasStation = gasStations.get(gasStationList.indexOf(stationTF.getText()));
                        selectedStationId = gasStation.getId();
                    }
                }
            }
        });

        materialTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(materialTF.getText().length()>0){
                    if(materialList.contains(materialTF.getText())){
                        final Material material = materials.get(materialList.indexOf(materialTF.getText()));
                        selectedMaterialId = material.getId();
                    }
                }
            }
        });

        buyerTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(buyerTF.getText().length()>0){
                    if(buyers.contains(buyerTF.getText())){
                        final Buyer buyer = buyerList.get(buyers.indexOf(buyerTF.getText()));
                        selectedBuyerId = buyer.getId();
                    }
                }
            }
        });
    }

    @FXML
    void submit() {

    }

    private static void notify(ReturnedMaterial returnedMaterial) {
        String message;
        if(Objects.nonNull(returnedMaterial.getId())){
            message = "returnedMaterial added";
        }else {
            message = "something went wrong";
        }
        controller.addData(returnedMaterial);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
