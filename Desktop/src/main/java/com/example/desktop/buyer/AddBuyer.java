package com.example.desktop.buyer;

import com.example.model.TableController;
import com.example.model.buyer.BuyerService;
import com.example.model.modal.Modal;
import com.example.model.tools.FormType;
import com.example.model.tools.QueryBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreateBuyerRequest;
import org.example.contract.request.update.UpdateBuyerRequest;
import org.example.model.Buyer;

import java.util.Objects;

public class AddBuyer {

    public static TableController controller;
    public static FormType formType = FormType.CREATE;
    private final BuyerService buyerService = BuyerService.getInstance();

    @FXML
    private TextField nameTF;

    @FXML
    private TextField organizationTF;

    @FXML
    private Button submitBtn;

    @FXML
    private void initialize(){
        if(formType.equals(FormType.UPDATE)){
            final Buyer buyer = buyerService.getItem(Buyers.selectedBuyer.getId());
            nameTF.setText(buyer.getName());
            organizationTF.setText(buyer.getOrganization());
        }
    }

    @FXML
    void submit() {
        final Buyer buyer;
        if(formType.equals(FormType.UPDATE)){
            buyer = buyerService.editItem(new UpdateBuyerRequest(Buyers.selectedBuyer.getId(), nameTF.getText(), organizationTF.getText()));
        }else if(formType.equals(FormType.CREATE)){
            buyer = buyerService.addItem(new CreateBuyerRequest(nameTF.getText(), organizationTF.getText()));
        }else {
            buyer = new Buyer();
            final QueryBuilder queryBuilder = new QueryBuilder();
            if(nameTF.getText().length()>0){
                queryBuilder.addQueryParameter("name", nameTF.getText());
            }
            if(organizationTF.getText().length()>0){
                queryBuilder.addQueryParameter("organization", organizationTF.getText());
            }
            queryBuilder.sort("id", "desc");
            controller.setQuery(queryBuilder.getQuery());
        }
        notify(buyer);
    }

    private static void notify(Buyer buyer){
        String message;
        if(Objects.nonNull(buyer.getId())){
            message = "buyer added";
        }else {
            message = "something went wrong";
        }
        controller.addData(buyer);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
