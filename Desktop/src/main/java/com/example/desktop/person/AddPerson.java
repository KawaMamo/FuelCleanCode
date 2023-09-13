package com.example.desktop.person;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.person.PersonService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreatePersonRequest;
import org.example.model.Group;
import org.example.model.Person;

import java.util.Objects;

public class AddPerson {

    public static TableController controller;
    public static Boolean isEditingForm = false;

    @FXML
    private DatePicker birthDateDP;

    @FXML
    private TextField birthPlaceTF;

    @FXML
    private TextField fatherTF;

    @FXML
    private TextField motherTF;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField nationalIdTF;

    @FXML
    private Button submitBtn;

    private final PersonService personService = PersonService.getInstance();

    @FXML
    void submit() {
        final Person person = personService.addItem(new CreatePersonRequest(nameTF.getText(),
                fatherTF.getText(),
                motherTF.getText(),
                nationalIdTF.getText(),
                birthPlaceTF.getText(),
                birthDateDP.getValue()));
        extracted(person);
    }

    private static void extracted(Person person) {
        String message;
        if(Objects.nonNull(person.getId())){
            message = "person added";
        }else {
            message = "something went wrong";
        }
        controller.addData(person);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
