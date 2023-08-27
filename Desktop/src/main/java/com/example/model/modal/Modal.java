package com.example.model.modal;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Modal {

    public static Object controller;
    public static Stage modal;

    public static void start(Class clazz, String resource){
        modal = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(clazz.getResource(resource));
        try {
            modal.setScene(new Scene(loader.load()));
            controller = loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.show();
    }

}
