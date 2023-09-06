package com.example.desktop.priceCategory;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.priceCategory.PriceCategoryService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreatePriceCategoryRequest;
import org.example.model.Group;
import org.example.model.PriceCategory;

import java.util.Objects;

public class AddPriceCategory {

    public static TableController controller;

    @FXML
    private TextField nameTF;

    @FXML
    private Button submitBtn;

    private final PriceCategoryService priceCategoryService = PriceCategoryService.getInstance();

    @FXML
    void submit() {
        final PriceCategory priceCategory = priceCategoryService.addItem(new CreatePriceCategoryRequest(nameTF.getText()));
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
