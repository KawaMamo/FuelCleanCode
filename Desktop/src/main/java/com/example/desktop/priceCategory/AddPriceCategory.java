package com.example.desktop.priceCategory;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.priceCategory.PriceCategoryService;
import com.example.model.tools.FormType;
import com.example.model.tools.QueryBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreatePriceCategoryRequest;
import org.example.contract.request.update.UpdatePriceCategoryRequest;
import org.example.model.PriceCategory;

import java.util.Objects;

public class AddPriceCategory {

    public static TableController controller;
    public static FormType formType = FormType.CREATE;
    @FXML
    private TextField nameTF;

    @FXML
    private Button submitBtn;

    private final PriceCategoryService priceCategoryService = PriceCategoryService.getInstance();

    @FXML
    private void initialize(){
        if(formType.equals(FormType.UPDATE)){
            final PriceCategory priceCategory = priceCategoryService.getItem(PriceCategories.selectdPriceCategory.getId());
            nameTF.setText(priceCategory.getName());
        }
    }
    @FXML
    void submit() {
        final PriceCategory priceCategory;
        if(formType.equals(FormType.UPDATE)){
            priceCategory = priceCategoryService.editItem(new UpdatePriceCategoryRequest(PriceCategories.selectdPriceCategory.getId(),
                    nameTF.getText()));
        }else if (formType.equals(FormType.CREATE)){
            priceCategory = priceCategoryService.addItem(new CreatePriceCategoryRequest(nameTF.getText()));
        }else {
            final QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.addQueryParameter("name", nameTF.getText(), "%3A");
            queryBuilder.sort("id", "desc");
            priceCategory = new PriceCategory();
            controller.setQuery(queryBuilder.getQuery());
        }
        extracted(priceCategory);
    }

    private static void extracted(PriceCategory priceCategory) {
        String message;
        if(Objects.nonNull(priceCategory.getId())){
            message = "priceCategory added";
        }else {
            message = "something went wrong";
        }
        controller.addData(priceCategory);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
