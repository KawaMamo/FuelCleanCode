package com.example.desktop.officePayment;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.office.OfficeService;
import com.example.model.officePayment.OfficePaymentService;
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
import org.example.contract.request.create.CreateClientPaymentRequest;
import org.example.contract.request.create.CreateOfficePaymentRequest;
import org.example.contract.request.update.UpdateOfficePaymentRequest;
import org.example.model.ClientPayment;
import org.example.model.Money;
import org.example.model.Office;
import org.example.model.OfficePayment;

import java.util.List;
import java.util.Objects;

public class AddOfficePayment {

    @FXML
    private TextField amountTF;

    @FXML
    private TextField billNumberTF;

    @FXML
    private ChoiceBox<String> currencyCB;

    @FXML
    private TextField officeTF;

    @FXML
    private TextField notesTF;

    @FXML
    private Button submitBtn;

    public static TableController controller;
    public static FormType formType = FormType.CREATE;
    private final OfficePaymentService officePaymentService = OfficePaymentService.getInstance();
    private Long selectedOfficeId;

    private final OfficeService officeService = OfficeService.getInstance();

    @FXML
    private void initialize(){

        currencyCB.getItems().addAll("USD", "SP");
        if(formType.equals(FormType.UPDATE)){
            final OfficePayment item = officePaymentService.getItem(OfficePayments.selectedOfficePayment.getId());
            officeTF.setText(item.getOffice().getName());
            if(Objects.nonNull(item.getBillNumber()))
                billNumberTF.setText(item.getBillNumber().toString());
            amountTF.setText(item.getAmount().getAmount().toString());
            currencyCB.setValue(item.getAmount().getCurrency());
            notesTF.setText(item.getNotes());
            selectedOfficeId = item.getOffice().getId();
        }
        final List<Office> officeList = officeService.getItems(null, null);
        final List<String> officeNames = officeList.stream().map(Office::getName).toList();
        TextFields.bindAutoCompletion(officeTF, officeNames);

        officeTF.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if(officeTF.getText().length()>0){
                    if(officeNames.contains(officeTF.getText())){
                        final Office office = officeList.get(officeNames.indexOf(officeTF.getText()));
                        selectedOfficeId=office.getId();
                    }
                }
            }
        });
    }

    @FXML
    void submit() {
        final OfficePayment officePayment;
        if(formType.equals(FormType.UPDATE)){
            officePayment = officePaymentService.editItem(new UpdateOfficePaymentRequest(OfficePayments.selectedOfficePayment.getId(),
                    new Money(currencyCB.getValue(), Double.parseDouble(amountTF.getText())),
                    Long.parseLong(billNumberTF.getText()),
                    notesTF.getText(),
                    selectedOfficeId));
        }else if(formType.equals(FormType.CREATE)){
            officePayment = officePaymentService.addItem(new CreateOfficePaymentRequest(new Money(currencyCB.getValue(),
                    Double.parseDouble(amountTF.getText())),
                    Long.parseLong(billNumberTF.getText()),
                    notesTF.getText(),
                    selectedOfficeId));
        }else {
            officePayment = new OfficePayment();
            final QueryBuilder queryBuilder = new QueryBuilder();
            if(Objects.nonNull(selectedOfficeId))
                queryBuilder.addQueryParameter("office", selectedOfficeId.toString());
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
        notify(officePayment);
    }

    private static void notify(OfficePayment officePayment) {
        String message;
        if(Objects.nonNull(officePayment.getId())){
            message = "officePayment added";
        }else {
            message = "something went wrong";
        }
        controller.addData(officePayment);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
