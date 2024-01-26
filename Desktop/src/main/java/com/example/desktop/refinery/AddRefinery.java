package com.example.desktop.refinery;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.refinery.RefineryService;
import com.example.model.region.RegionService;
import com.example.model.tools.FormType;
import com.example.model.tools.QueryBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import org.example.contract.request.create.CreateRefineryRequest;
import org.example.contract.request.update.UpdateRefineryRequest;
import org.example.model.Refinery;
import org.example.model.Region;

import java.util.List;
import java.util.Objects;

public class AddRefinery {

    public static TableController controller;
    public static FormType formType = FormType.CREATE;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField translationTF;

    @FXML
    private TextField regionTF;

    @FXML
    private Button submitBtn;

    private final RefineryService refineryService = RefineryService.getInstance();
    private final RegionService regionService = RegionService.getInstance();

    private Long selectedRegionId;

    @FXML
    private void initialize(){
        if(formType.equals(FormType.UPDATE)){
            final Refinery refinery = refineryService.getItem(Refineries.selectedRefinery.getId());
            nameTF.setText(refinery.getName());
            translationTF.setText(refinery.getTranslation());
            regionTF.setText(refinery.getRegion().getName());
            selectedRegionId = refinery.getRegion().getId();
        }

        final List<Region> regions = regionService.getItems(null, null);
        final List<String> regionNames = regions.stream().map(Region::getName).toList();
        TextFields.bindAutoCompletion(regionTF, regionNames);

        regionTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(regionTF.getText().length()>0){
                    if(regionNames.contains(regionTF.getText())){
                        final Region region = regions.get(regionNames.indexOf(regionTF.getText()));
                        if(Objects.nonNull(region))
                            selectedRegionId = region.getId();
                    }
                }
            }
        });

    }
    @FXML
    void submit() {
        final Refinery refinery;
        if(formType.equals(FormType.UPDATE)){
            refinery = refineryService.editItem(new UpdateRefineryRequest(Refineries.selectedRefinery.getId(),
                    nameTF.getText(),
                    translationTF.getText(),
                    selectedRegionId));
        }else if(formType.equals(FormType.CREATE)){
            refinery = refineryService.addItem(new CreateRefineryRequest(nameTF.getText(), translationTF.getText(), selectedRegionId));
        }else {
            refinery = new Refinery();
            QueryBuilder queryBuilder = new QueryBuilder();
            if(nameTF.getText().length()>0)
                queryBuilder.addQueryParameter("name", nameTF.getText());
            if(translationTF.getText().length()>0)
                queryBuilder.addQueryParameter("translation", translationTF.getText());
            if(Objects.nonNull(selectedRegionId))
                queryBuilder.addQueryParameter("regionEntity", selectedRegionId.toString());
            queryBuilder.sort();
            controller.setQuery(queryBuilder.getQuery());
        }
        notify(refinery);
    }

    private static void notify(Refinery refinery) {
        String message;
        if(Objects.nonNull(refinery.getId())){
            message = "refinery added";
        }else {
            message = "something went wrong";
        }
        controller.addData(refinery);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
