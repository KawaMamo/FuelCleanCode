package com.example.desktop.group;

import com.example.model.TableController;
import com.example.model.group.GroupService;
import com.example.model.modal.Modal;
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
    public static Boolean isEditingForm = false;

    @FXML
    private TextField nameTF;

    @FXML
    private Button submitBtn;

    private final GroupService groupService = GroupService.getInstance();

    @FXML
    private void initialize(){
        if(isEditingForm){
            final Group group = groupService.getItem(Groups.selectedGroup.getId());
            nameTF.setText(group.getName());
        }
    }

    @FXML
    void submit() {
        final Group group;
        if(isEditingForm){
            group = groupService.editItem(new UpdateGroupRequest(Groups.selectedGroup.getId(), nameTF.getText()));
        }else {
            group = groupService.addItem(new CreateGroupRequest(nameTF.getText()));
        }
        isEditingForm = false;
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
