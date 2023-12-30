package com.example.desktop.person;

import com.example.model.TableController;
import com.example.model.modal.Modal;
import com.example.model.person.PersonService;
import com.example.model.tools.FormType;
import com.example.model.tools.QueryBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreatePersonRequest;
import org.example.contract.request.update.UpdatePersonRequest;
import org.example.model.Group;
import org.example.model.Person;

import java.time.LocalDate;
import java.util.Objects;

public class AddPerson {

    public static TableController controller;
    public static FormType formType = FormType.CREATE;

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
    private void initialize(){
        if(formType.equals(FormType.UPDATE)){
            final Person person = personService.getItem(Persons.selectedPerson.getId());
            nameTF.setText(person.getName());
            fatherTF.setText(person.getFather());
            motherTF.setText(person.getMother());
            nationalIdTF.setText(person.getNationalId());
            birthPlaceTF.setText(person.getBirthPlace());
            birthDateDP.setValue(person.getBirthDate());
        }
    }
    @FXML
    void submit() {
        final Person person;
        if(formType.equals(FormType.UPDATE)){
            person = personService.editItem(new UpdatePersonRequest(Persons.selectedPerson.getId(),
                    nameTF.getText(),
                    fatherTF.getText(),
                    motherTF.getText(),
                    nationalIdTF.getText(),
                    birthPlaceTF.getText(),
                    birthDateDP.getValue()));
        }else if(formType.equals(FormType.CREATE)){
            person = personService.addItem(new CreatePersonRequest(nameTF.getText(),
                    fatherTF.getText(),
                    motherTF.getText(),
                    nationalIdTF.getText(),
                    birthPlaceTF.getText(),
                    birthDateDP.getValue()));
        }else {
            person = new Person();
            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.addQueryParameter("name", nameTF.getText());
            queryBuilder.addQueryParameter("father", fatherTF.getText());
            queryBuilder.addQueryParameter("mother", motherTF.getText());
            queryBuilder.addQueryParameter("birthPlace", birthPlaceTF.getText());
            queryBuilder.sort();
            controller.setQuery(queryBuilder.getQuery());
        }
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
