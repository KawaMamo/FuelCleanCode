package com.example.desktop.gasStation;

import com.example.model.TableController;
import com.example.model.gasStation.GasStationService;
import com.example.model.group.GroupService;
import com.example.model.material.MaterialService;
import com.example.model.modal.Modal;
import com.example.model.person.PersonService;
import com.example.model.priceCategory.PriceCategoryService;
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
import org.example.contract.request.create.CreateGasStationRequest;
import org.example.contract.request.update.UpdateGasStationRequest;
import org.example.model.*;

import java.util.List;
import java.util.Objects;

public class AddGasStation {

    public static FormType formType = FormType.CREATE;

    @FXML
    private TextField groupTF;

    @FXML
    private TextField nameTF;
    @FXML
    private TextField translationTF;

    @FXML
    private TextField ownerTF;

    @FXML
    private TextField priceCatTF;

    @FXML
    private TextField regionTF;
    @FXML
    private TextField materialTF;

    @FXML
    private Button submitBtn;

    public static TableController controller;
    private final GasStationService gasStationService = GasStationService.getInstance();
    private final PriceCategoryService priceCategoryService = PriceCategoryService.getInstance();
    private final GroupService groupService = GroupService.getInstance();
    private final PersonService personService = PersonService.getInstance();
    private final RegionService regionService = RegionService.getInstance();
    private final MaterialService materialService = MaterialService.getInstance();
    private Long selectedOwnerId;
    private Long selectedPriceCatId;
    private Long selectedGroupId;
    private Long selectedRegionId;
    private Long selectedMaterialId;
    @FXML
    void initialize() {

        final List<Person> personList = personService.getItems(null, null);
        final List<String> stringList = personList.stream().map(person -> person.getName()+" "+person.getNationalId()).toList();
        TextFields.bindAutoCompletion(ownerTF, stringList);

        ownerTF.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(ownerTF.getText().length() > 0) {
                if(stringList.contains(ownerTF.getText())){
                    final Person person = personList.get(stringList.indexOf(ownerTF.getText()));
                    if(Objects.nonNull(person))
                        selectedOwnerId = person.getId();
                }
            }
        });

        final List<PriceCategory> priceCategoryList = priceCategoryService.getItems(null, null);
        final List<String> priceCategoryNames = priceCategoryList.stream().map(PriceCategory::getName).toList();
        TextFields.bindAutoCompletion(priceCatTF, priceCategoryNames);

        priceCatTF.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if(priceCatTF.getText().length() > 0){
                if(priceCategoryNames.contains(priceCatTF.getText())){
                    final PriceCategory priceCategory = priceCategoryList.get(priceCategoryNames.indexOf(priceCatTF.getText()));
                    if(Objects.nonNull(priceCategory)){
                        selectedPriceCatId = priceCategory.getId();
                    }
                }
            }
        });

        final List<Group> groupList =  groupService.getItems(null, null);
        final List<String> groupNames = groupList.stream().map(Group::getName).toList();
        TextFields.bindAutoCompletion(groupTF, groupNames);

        groupTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(groupTF.getText().length()>0){
                    if(groupNames.contains(groupTF.getText())){
                        final Group group = groupList.get(groupNames.indexOf(groupTF.getText()));
                        if(Objects.nonNull(group))
                            selectedGroupId = group.getId();
                    }
                }
            }
        });

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

        final List<Material> materials = materialService.getItems(null, null);
        final List<String> materialNames = materials.stream().map(Material::getName).toList();
        TextFields.bindAutoCompletion(materialTF, materialNames);

        materialTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(materialTF.getText().length()>0){
                    if(materialNames.contains(materialTF.getText())){
                        final Material material = materials.get(materialNames.indexOf(materialTF.getText()));
                        if(Objects.nonNull(material))
                            selectedMaterialId = material.getId();
                    }
                }
            }
        });

        if(formType.equals(FormType.UPDATE)){
            final GasStation gasStation = gasStationService.getItem(GasStations.selectedGasStation.getId());
            nameTF.setText(gasStation.getName());
            ownerTF.setText(gasStation.getOwner().getName());
            priceCatTF.setText(gasStation.getPriceCategory().getName());
            regionTF.setText(gasStation.getRegion().getName());
            groupTF.setText(gasStation.getGroup().getName());
            materialTF.setText(gasStation.getMaterial().getName());
            selectedOwnerId = gasStation.getOwner().getId();
            selectedPriceCatId = gasStation.getPriceCategory().getId();
            selectedRegionId = gasStation.getRegion().getId();
            selectedGroupId = gasStation.getGroup().getId();
            selectedMaterialId = gasStation.getMaterial().getId();
            translationTF.setText(gasStation.getTranslation());
        }
    }
    @FXML
    void submit() {
        final GasStation gasStation;
        if(formType.equals(FormType.UPDATE)){
            gasStation = gasStationService.editItem(new UpdateGasStationRequest(GasStations.selectedGasStation.getId(),
                    nameTF.getText(),
                    translationTF.getText(),
                    selectedPriceCatId,
                    selectedRegionId,
                    selectedOwnerId,
                    selectedGroupId,
                    selectedMaterialId));
        }else if(formType.equals(FormType.CREATE)){
            gasStation = gasStationService.addItem(new CreateGasStationRequest(nameTF.getText(),
                    translationTF.getText(),
                    selectedPriceCatId,
                    selectedRegionId,
                    selectedOwnerId,
                    selectedGroupId,
                    selectedMaterialId));
        }else {
            gasStation = new GasStation();
            final QueryBuilder queryBuilder = new QueryBuilder();
            if(nameTF.getText().length()>0)
                queryBuilder.addQueryParameter("name", nameTF.getText());
            if(translationTF.getText().length()>0)
                queryBuilder.addQueryParameter("translation", translationTF.getText());
            if(Objects.nonNull(selectedPriceCatId))
                queryBuilder.addQueryParameter("priceCategory", selectedPriceCatId.toString());
            if(Objects.nonNull(selectedRegionId))
                queryBuilder.addQueryParameter("region", selectedRegionId.toString());
            if(Objects.nonNull(selectedOwnerId))
                queryBuilder.addQueryParameter("owner", selectedOwnerId.toString());
            if(Objects.nonNull(selectedGroupId))
                queryBuilder.addQueryParameter("group", selectedGroupId.toString());
            if(Objects.nonNull(selectedMaterialId))
                queryBuilder.addQueryParameter("material", selectedMaterialId.toString());
            controller.setQuery(queryBuilder.getQuery());
        }
        notify(gasStation);
    }

    private static void notify(GasStation gasStation) {
        String message;
        if(Objects.nonNull(gasStation.getId())){
            message = "gasStation added";
        }else {
            message = "something went wrong";
        }
        controller.addData(gasStation);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
