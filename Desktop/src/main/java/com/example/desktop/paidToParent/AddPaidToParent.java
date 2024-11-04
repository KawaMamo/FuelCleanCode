package com.example.desktop.paidToParent;

import com.example.desktop.officePayment.OfficePayments;
import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.paidToParent.PaidToParentService;
import com.example.model.tools.FormType;
import com.example.model.tools.QueryBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import org.example.contract.request.create.CreateOfficePaymentRequest;
import org.example.contract.request.create.CreatePaidToParentRequest;
import org.example.contract.request.update.UpdateOfficePaymentRequest;
import org.example.contract.request.update.UpdatePaidToParentRequest;
import org.example.model.Money;
import org.example.model.Office;
import org.example.model.OfficePayment;
import org.example.model.PaidToParent;

import java.util.List;
import java.util.Objects;

public class AddPaidToParent {
    @FXML
    private TextField amountTF;

    @FXML
    private TextField billNumberTF;

    @FXML
    private ChoiceBox<String> currencyCB;

    @FXML
    private TextField notesTF;

    @FXML
    private Button submitBtn;

    public static TableController controller;
    public static FormType formType = FormType.CREATE;
    private final PaidToParentService service = PaidToParentService.getInstance();

    @FXML
    void submit() {
        final PaidToParent paidToParent;
        if(formType.equals(FormType.UPDATE)){
            paidToParent = service.editItem(new UpdatePaidToParentRequest(PaidToParents.selectedPaidToParent.getId(),
                    new Money(currencyCB.getValue(), Double.parseDouble(amountTF.getText())),
                    Long.parseLong(billNumberTF.getText()),
                    notesTF.getText()));
        }else if(formType.equals(FormType.CREATE)){
            paidToParent = service.addItem(new CreatePaidToParentRequest(new Money(currencyCB.getValue(),
                    Double.parseDouble(amountTF.getText())),
                    Long.parseLong(billNumberTF.getText()),
                    notesTF.getText()));
        }else {
            paidToParent = new PaidToParent();
            final QueryBuilder queryBuilder = new QueryBuilder();

            if(billNumberTF.getText().length()>1)
                queryBuilder.addQueryParameter("billNumber", billNumberTF.getText());
            if(amountTF.getText().length()>0)
                queryBuilder.addQueryParameter("priceAmount", amountTF.getText());
            if(Objects.nonNull(currencyCB.getValue()))
                queryBuilder.addQueryParameter("priceCurrency", currencyCB.getValue());
            if(notesTF.getText().length()>0)
                queryBuilder.addQueryParameter("notes", notesTF.getText());

            queryBuilder.sort("id", "desc");
            controller.setQuery(queryBuilder.getQuery());
        }
        notify(paidToParent);
    }

    private void notify(PaidToParent paidToParent) {
        String message;
        if(Objects.nonNull(paidToParent.getId())){
            message = "paidToParent added";
        }else {
            message = "something went wrong";
        }
        controller.addData(paidToParent);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }


    @FXML
    private void initialize(){

        currencyCB.getItems().addAll("USD", "SP");
        if(formType.equals(FormType.UPDATE)){
            final PaidToParent item = service.getItem(PaidToParents.selectedPaidToParent.getId());
            if(Objects.nonNull(item.getBillNumber()))
                billNumberTF.setText(item.getBillNumber().toString());
            amountTF.setText(item.getAmount().getAmount().toString());
            currencyCB.setValue(item.getAmount().getCurrency());
            notesTF.setText(item.getNotes());
        }
     }
}
