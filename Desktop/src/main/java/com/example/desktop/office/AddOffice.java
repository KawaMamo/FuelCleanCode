package com.example.desktop.office;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.office.OfficeService;
import com.example.model.tools.FormType;
import com.example.model.tools.QueryBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreateOfficeRequest;
import org.example.contract.request.update.UpdateOfficeRequest;
import org.example.model.Office;

import java.util.Objects;

public class AddOffice {

    public static FormType formType = FormType.CREATE;
    @FXML
    private TextField nameTF;

    @FXML
    private Button submitBtn;

    public static TableController controller;
    private final OfficeService officeService = OfficeService.getInstance();

    @FXML
    private void initialize(){
        if(formType.equals(FormType.UPDATE)){
            final Office office = officeService.getItem(Offices.slectedOffice.getId());
            nameTF.setText(office.getName());
        }
    }
    @FXML
    void submit() {
        final Office office;
        if(formType.equals(FormType.UPDATE)){
            office = officeService.editItem(new UpdateOfficeRequest(Offices.slectedOffice.getId(), nameTF.getText()));
        }else if(formType.equals(FormType.CREATE)){
            office = officeService.addItem(new CreateOfficeRequest(nameTF.getText()));
        }else {
            office = new Office();
            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.addQueryParameter("name", nameTF.getText());
            queryBuilder.sort();
            controller.setQuery(queryBuilder.getQuery());
        }
        notify(office);
    }

    private static void notify(Office office) {
        String message;
        if(Objects.nonNull(office.getId())){
            message = "office added";
        }else {
            message = "something went wrong";
        }
        controller.addData(office);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
