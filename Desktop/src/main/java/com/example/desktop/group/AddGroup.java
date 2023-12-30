package com.example.desktop.group;

import com.example.model.TableController;
import com.example.model.group.GroupService;
import com.example.model.modal.Modal;
import com.example.model.tools.FormType;
import com.example.model.tools.QueryBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;
import org.example.contract.request.create.CreateGroupRequest;
import org.example.contract.request.update.UpdateGroupRequest;
import org.example.model.Group;

import java.util.Objects;

public class AddGroup {

    public static TableController controller;
    public static FormType formType = FormType.CREATE;

    @FXML
    private TextField nameTF;

    @FXML
    private Button submitBtn;

    private final GroupService groupService = GroupService.getInstance();

    @FXML
    private void initialize(){
        if(formType.equals(FormType.UPDATE)){
            final Group group = groupService.getItem(Groups.selectedGroup.getId());
            nameTF.setText(group.getName());
        }
    }

    @FXML
    void submit() {
        final Group group;
        if(formType.equals(FormType.UPDATE)){
            group = groupService.editItem(new UpdateGroupRequest(Groups.selectedGroup.getId(), nameTF.getText()));
        }else if(formType.equals(FormType.CREATE)){
            group = groupService.addItem(new CreateGroupRequest(nameTF.getText()));
        }else {
            group = new Group();
            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.addQueryParameter("name", nameTF.getText());
            queryBuilder.sort("id", "desc");
            controller.setQuery(queryBuilder.getQuery());
        }
        extracted(group);
    }

    private static void extracted(Group group) {
        String message;
        if(Objects.nonNull(group.getId())){
            message = "group added";
        }else {
            message = "something went wrong";
        }
        controller.addData(group);
        Notifications.create().title("Info").text(message).showInformation();
        Modal.close();
    }
}
