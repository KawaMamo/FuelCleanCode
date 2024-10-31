package com.example.desktop.seller;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.seller.SellerService;
import com.example.model.tools.FormType;
import com.example.model.tools.QueryBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreateSellerRequest;
import org.example.contract.request.update.UpdateSellerRequest;
import org.example.model.Seller;

import java.util.Objects;

public class AddSeller {
    @FXML
    private TextField nameTF;

    @FXML
    private Button submitBtn;

    public static TableController controller;
    public static FormType formType = FormType.CREATE;
    private final SellerService service = SellerService.getInstance();

    @FXML
    private void initialize(){
        if(formType.equals(FormType.UPDATE)){
            final Seller seller = service.getItem(Sellers.selectedSeller.getId());
            nameTF.setText(seller.getName());
        }
    }

    @FXML
    void submit() {
        final Seller seller;
        if(formType.equals(FormType.UPDATE)){
            seller = service.editItem(new UpdateSellerRequest(Sellers.selectedSeller.getId(), nameTF.getText()));
        }else if(formType.equals(FormType.CREATE)){
            seller = service.addItem(new CreateSellerRequest(nameTF.getText()));
        }else {
            seller = new Seller();
            final QueryBuilder queryBuilder = new QueryBuilder();
            if(nameTF.getText().length()>0){
                queryBuilder.addQueryParameter("name", nameTF.getText());
            }

            queryBuilder.sort("id", "desc");
            controller.setQuery(queryBuilder.getQuery());
        }
        notify(seller);
    }

    private void notify(Seller seller) {
        String message;
        if(Objects.nonNull(seller.getId())){
            message = "seller added";
        }else {
            message = "something went wrong";
        }
        controller.addData(seller);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
