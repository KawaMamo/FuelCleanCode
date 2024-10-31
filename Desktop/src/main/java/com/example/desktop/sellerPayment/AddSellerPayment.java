package com.example.desktop.sellerPayment;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.seller.SellerService;
import com.example.model.sellerPayment.SellerPaymentService;
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
import org.example.contract.request.create.CreateSellerPaymentRequest;
import org.example.contract.request.update.UpdateSellerPaymentRequest;
import org.example.model.*;

import java.util.List;
import java.util.Objects;

public class AddSellerPayment {

    @FXML
    private TextField amountTF;

    @FXML
    private TextField billNumberTF;

    @FXML
    private ChoiceBox<String> currencyCB;

    @FXML
    private TextField notesTF;

    @FXML
    private TextField sellerTF;

    @FXML
    private Button submitBtn;

    public static TableController controller;
    public static FormType formType = FormType.CREATE;
    private final SellerPaymentService service = SellerPaymentService.getInstance();
    private Long selectedSellerId;
    private final SellerService sellerService = SellerService.getInstance();

    @FXML
    private void initialize(){

        currencyCB.getItems().addAll("USD", "SP");
        if(formType.equals(FormType.UPDATE)){
            final SellerPayment item = service.getItem(SellerPayments.selectedSellerPayment.getId());
            sellerTF.setText(item.getSeller().getName());
            if(Objects.nonNull(item.getBillNumber()))
                billNumberTF.setText(item.getBillNumber().toString());
            amountTF.setText(item.getAmount().getAmount().toString());
            currencyCB.setValue(item.getAmount().getCurrency());
            notesTF.setText(item.getNotes());
            selectedSellerId = item.getSeller().getId();
        }
        final List<Seller> sellerList = sellerService.getItems(null, null);
        final List<String> sellerNames = sellerList.stream().map(Seller::getName).toList();
        TextFields.bindAutoCompletion(sellerTF, sellerNames);

        sellerTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(sellerTF.getText().length()>0){
                    if(sellerNames.contains(sellerTF.getText())){
                        final Seller seller = sellerList.get(sellerNames.indexOf(sellerTF.getText()));
                        selectedSellerId=seller.getId();
                    }
                }
            }
        });
    }

    @FXML
    void submit() {
        final SellerPayment sellerPayment;
        if(formType.equals(FormType.UPDATE)){
            sellerPayment = service.editItem(new UpdateSellerPaymentRequest(SellerPayments.selectedSellerPayment.getId(),
                    new Money(currencyCB.getValue(), Double.parseDouble(amountTF.getText())),
                    Long.parseLong(billNumberTF.getText()),
                    notesTF.getText(),
                    selectedSellerId));
        }else if(formType.equals(FormType.CREATE)){
            sellerPayment = service.addItem(new CreateSellerPaymentRequest(new Money(currencyCB.getValue(),
                    Double.parseDouble(amountTF.getText())),
                    Long.parseLong(billNumberTF.getText()),
                    notesTF.getText(),
                    selectedSellerId));
        }else {
            sellerPayment = new SellerPayment();
            final QueryBuilder queryBuilder = new QueryBuilder();
            if(Objects.nonNull(selectedSellerId))
                queryBuilder.addQueryParameter("seller", selectedSellerId.toString());
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
        notify(sellerPayment);
    }

    private void notify(SellerPayment sellerPayment) {
        String message;
        if(Objects.nonNull(sellerPayment.getId())){
            message = "sellerPayment added";
        }else {
            message = "something went wrong";
        }
        controller.addData(sellerPayment);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
