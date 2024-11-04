package com.example.desktop.transportation;

import com.example.model.TableController;
import com.example.model.forfeit.ForfeitService;
import com.example.model.modal.Modal;
import com.example.model.tools.FormType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreateForfeitRequest;
import org.example.contract.request.update.UpdateForfeitRequest;
import org.example.model.Forfeit;
import org.example.model.Money;

import java.util.Objects;

public class AddForfeit {
    @FXML
    private TextField amountTF;

    @FXML
    private ChoiceBox<String> currencyCB;

    @FXML
    private Button forfeitBtn;

    @FXML
    private TextField reasonTF;


    public static TableController controller;
    public static FormType formType = FormType.CREATE;
    private final ForfeitService service = ForfeitService.getInstance();

    @FXML
    private void initialize(){
        if(formType.equals(FormType.UPDATE)){
            final Forfeit forfeit = service.getItem(Transportations.selectedForfeit.getId());
            reasonTF.setText(forfeit.getReason());
            amountTF.setText(forfeit.getPrice().getAmount().toString());
            currencyCB.setValue(forfeit.getPrice().getCurrency());
        }
    }

    @FXML
    void submit() {
        final Forfeit forfeit;
        if(formType.equals(FormType.UPDATE)){
            forfeit = service.editItem(new UpdateForfeitRequest(Transportations.selectedForfeit.getId(),
                    Transportations.selectedTransportation.getVehicle().getId(),
                    AddTransportation.selectedPartition.getId(),
                    new Money(currencyCB.getValue(), Double.valueOf(amountTF.getText())),
                    reasonTF.getText()));
        }else if(formType.equals(FormType.CREATE)){
            forfeit = service.addItem(new CreateForfeitRequest(AddTransportation.selectedVehicleId,
                    AddTransportation.selectedPartition.getId(),
                    new Money(currencyCB.getValue(), Double.valueOf(amountTF.getText())),
                    reasonTF.getText()));
        }else forfeit = new Forfeit();
        notify(forfeit);
    }

    private void notify(Forfeit forfeit){
        String message;
        if(Objects.nonNull(forfeit.getId())){
            message = "forfeit added";
        }else {
            message = "something went wrong";
        }
        controller.addData(forfeit);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
