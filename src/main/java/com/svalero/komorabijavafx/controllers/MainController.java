package com.svalero.komorabijavafx.controllers;

import com.svalero.komorabijavafx.tasks.ListTask;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;

public class MainController {
    @FXML
    private TabPane container;

    @FXML
    private Button clientsBtn;

    @FXML
    private Button gardenersBtn;

    @FXML
    protected void onSelectList(Event event) {
        Button sourceButton = (Button) event.getSource();
        String option;
        if(sourceButton == clientsBtn)
            option = "clients";
        else option = "gardeners";
        ListTask listTask = new ListTask(option, container);
        controlListTask(listTask);
        Thread thread = new Thread(listTask);
        //Close thread if app exit
        thread.setDaemon(true);
        thread.start();
    }

    private void controlListTask(ListTask task){
        task.setOnSucceeded(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Notificacion");
            alert.setContentText("Listing completed");
            alert.show();
        });
        task.setOnFailed(event -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Notificacion");
            alert.setContentText("Error listing");
            alert.show();
        });
        task.setOnCancelled(event -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Notificacion");
            alert.setContentText("Listing cancelled");
            alert.show();
        });
    }
}
