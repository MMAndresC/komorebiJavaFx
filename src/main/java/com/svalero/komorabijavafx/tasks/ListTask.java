package com.svalero.komorabijavafx.tasks;


import com.google.gson.reflect.TypeToken;
import com.svalero.komorabijavafx.models.Project;
import com.svalero.komorabijavafx.models.School;
import com.svalero.komorabijavafx.utils.DateUtil;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.List;

import com.google.gson.Gson;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public  class ListTask extends Task<Void> {

    private final String entity;
    private final TabPane tabPane;

    private final String BASE_URL = "http://localhost:8081/";

    public ListTask(String entity, TabPane tab){
        this.entity = entity;
        this.tabPane = tab;
    }
    @Override
    protected Void call() throws Exception {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create( BASE_URL + this.entity ))
                    .GET()
                    .build();
            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(response -> {
                        Gson gson = new Gson();
                        try {
                            if (this.entity.equals("schools")) {
                                Type listType = new TypeToken<List<School>>() {
                                }.getType();
                                List<School> schools = gson.fromJson(response, listType);
                                createSchoolTableView(schools);
                            } else if (this.entity.equals("projects")) {
                                Type listType = new TypeToken<List<Project>>() {
                                }.getType();
                                List<Project> projects = gson.fromJson(response, listType);
                                createprojectTableView(projects);
                            }
                        }catch (Exception e) {
                            System.out.println(e.getMessage());
                            e.printStackTrace();
                        }
                    })
                    .exceptionally(e -> {
                        return null;
                    });

        }
        return null;
    }

    private void createSchoolTableView(List<School> schools){
        TableView<School> tableView = new TableView<>();

        TableColumn<School, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<School, String> cityColumn = new TableColumn<>("City");
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn<School, Integer> studentsColumn = new TableColumn<>("Number of students");
        studentsColumn.setCellValueFactory(new PropertyValueFactory<>("students"));

        TableColumn<School, Boolean> publicColumn = new TableColumn<>("Is public?");
        publicColumn.setCellValueFactory(new PropertyValueFactory<>("publicSchool"));

        TableColumn<School, String> registerDateColumn = new TableColumn<>("Register date");
        registerDateColumn.setCellValueFactory(cellData -> {
            String rawDate = cellData.getValue().getRegisterDate();

            String formattedDate = DateUtil.formatFromString(
                    rawDate,
                    "dd/MM/yyyy",
                    "yyyy-MM-dd"
            );

            return new ReadOnlyStringWrapper(formattedDate);
        });


        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(cityColumn);
        tableView.getColumns().add(studentsColumn);
        tableView.getColumns().add(publicColumn);
        tableView.getColumns().add(registerDateColumn);
        ObservableList<School> observableSchools = FXCollections.observableArrayList(schools);

        tableView.setItems(observableSchools);

        Tab clientTab = new Tab(this.entity);
        clientTab.setClosable(true);

        Platform.runLater(() -> {
            try{
                clientTab.setContent(tableView);
                tabPane.getTabs().add(clientTab);
            }catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        });
    }

    private void createprojectTableView(List<Project> projects){
        TableView<Project> tableView = new TableView<>();
        TableColumn<Project, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Project, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        //Instead the number show the description
        TableColumn<Project, Integer> odsColumn = new TableColumn<>("ODS");
        odsColumn.setCellValueFactory(new PropertyValueFactory<>("ods"));

        TableColumn<Project, Boolean> activeColumn = new TableColumn<>("Active");
        activeColumn.setCellValueFactory(new PropertyValueFactory<>("active"));

        TableColumn<Project, String> startDateColumn = new TableColumn<>("Start date");
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));


        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(descriptionColumn);
        tableView.getColumns().add(odsColumn);
        tableView.getColumns().add(activeColumn);
        tableView.getColumns().add(startDateColumn);

        ObservableList<Project> observableGardeners = FXCollections.observableArrayList(projects);

        tableView.setItems(observableGardeners);

        Tab gardenerTab = new Tab(this.entity);
        gardenerTab.setClosable(true);

        Platform.runLater(() -> {
            try{
                gardenerTab.setContent(tableView);
                tabPane.getTabs().add(gardenerTab);
            }catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
