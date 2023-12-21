package com.example.desktop.category;

import com.example.model.TableController;
import com.example.model.category.CategoryService;
import com.example.model.material.MaterialService;
import com.example.model.modal.Modal;
import com.example.model.priceCategory.PriceCategoryService;
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
import org.example.contract.request.create.CreateCategoryRequest;
import org.example.contract.request.update.UpdateCategoryRequest;
import org.example.model.Category;
import org.example.model.Material;
import org.example.model.Money;
import org.example.model.PriceCategory;

import java.util.List;
import java.util.Objects;

public class AddCategory {

    public static TableController controller;
    public static FormType formType = FormType.CREATE;
    private final PriceCategoryService priceCategoryService = PriceCategoryService.getInstance();
    private final MaterialService materialService = MaterialService.getInstance();

    @FXML
    private TextField amountTF;

    @FXML
    private ChoiceBox<String> currencyCB;

    @FXML
    private TextField materialTF;

    @FXML
    private TextField priceCategoryTF;

    @FXML
    private Button submitBtn;
    private final CategoryService categoryService = CategoryService.getInstance();

    private Long selectedPriceCategoryId;
    private Long selectedMaterialId;

    @FXML
    private void initialize(){
        currencyCB.getItems().add("USD");
        currencyCB.getItems().add("SP");
        if(formType.equals(FormType.UPDATE)){
            final Category category = categoryService.getItem(Categories.selectedCategory.getId());
            priceCategoryTF.setText(category.getPriceCategory().getName());
            materialTF.setText(category.getMaterial().getName());
            amountTF.setText(category.getPrice().getAmount().toString());
            currencyCB.setValue(category.getPrice().getCurrency());
            selectedMaterialId = category.getMaterial().getId();
            selectedPriceCategoryId = category.getPriceCategory().getId();
        }

        final List<Material> materialList = materialService.getItems(null, null);
        final List<PriceCategory> priceCategoryList = priceCategoryService.getItems(null, null);
        final List<String> materials = materialList.stream().map(Material::getName).toList();
        final List<String> priceCategories = priceCategoryList.stream().map(PriceCategory::getName).toList();

        TextFields.bindAutoCompletion(materialTF, materials);
        TextFields.bindAutoCompletion(priceCategoryTF, priceCategories);
        priceCategoryTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(priceCategoryTF.getText().length()>0){
                    if(priceCategories.contains(priceCategoryTF.getText())){
                        final PriceCategory priceCategory = priceCategoryList.get(priceCategories.indexOf(priceCategoryTF.getText()));
                        selectedPriceCategoryId = priceCategory.getId();
                    }
                }
            }
        });

        materialTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(materialTF.getText().length()>0){
                    if(materials.contains(materialTF.getText())){
                        final Material material = materialList.get(materials.indexOf(materialTF.getText()));
                        selectedMaterialId = material.getId();
                    }
                }
            }
        });
    }

    @FXML
    void submit() {
        final Category category;
        if(formType.equals(FormType.UPDATE)){
            category = categoryService.editItem(new UpdateCategoryRequest(Categories.selectedCategory.getId(),
                    selectedPriceCategoryId,
                    selectedMaterialId,
                    new Money(currencyCB.getValue(), Double.parseDouble(amountTF.getText()))));
        }else if(formType.equals(FormType.CREATE)){
            category = categoryService.addItem(new CreateCategoryRequest(selectedPriceCategoryId, selectedMaterialId, new Money(currencyCB.getValue(),
                    Double.parseDouble(amountTF.getText()))));
        }else {
            category = new Category();
            final QueryBuilder queryBuilder = new QueryBuilder();
            if(Objects.nonNull(selectedPriceCategoryId))
                queryBuilder.addQueryParameter("priceCategory", selectedPriceCategoryId.toString(), "%3A");
            if(Objects.nonNull(selectedMaterialId))
                queryBuilder.addQueryParameter("material", selectedMaterialId.toString(), "%3A");
            if(amountTF.getText().length()>0)
                queryBuilder.addQueryParameter("priceAmount", amountTF.getText(), "%3A");
            if(Objects.nonNull(currencyCB.getValue()))
                queryBuilder.addQueryParameter("priceCurrency", currencyCB.getValue());
            queryBuilder.sort("id", "desc");
            controller.setQuery(queryBuilder.getQuery());
        }
        notify(category);
    }

    private static void notify(Category category) {
        String message;
        if(Objects.nonNull(category.getId())){
            message = "category added";
        }else {
            message = "something went wrong";
        }
        controller.addData(category);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
