package com.example.desktop;

import com.example.model.exception.ExceptionHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class HelloApplication extends Application {
    private static Stage primaryStage;
    private static final ExceptionHandler exceptionHandler =ExceptionHandler.getInstance();
    @Override
    public void start(Stage stage) throws IOException {
        Thread.setDefaultUncaughtExceptionHandler(HelloApplication::showError);
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("LogIn!");
        stage.setScene(scene);
        stage.show();
    }

    public static void setScene(String path){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(path+".fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setScene(scene);
        } catch (IOException e) {
            exceptionHandler.setE(e);
        }
    }

    private static void showError(Thread t, Throwable e) {
        while (e.getCause() instanceof InvocationTargetException)
            e = e.getCause();
        Notifications.create().title("Error").text(e.getCause().getMessage()).showError();
    }

    public static void main(String[] args) {
        launch();
    }
}