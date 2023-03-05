package com.example.test;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;


public class TodoApplication extends Application {
    private ListView<String> listView = new ListView<>();
    private LinkedList<Task> taskList = new LinkedList<>();
    ObservableList<String> todoListItems = listView.getItems();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void start(Stage primaryStage) {
        // Set up the UI
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label descriptionLabel = new Label("Description:");
        GridPane.setConstraints(descriptionLabel, 0, 0);
        grid.getChildren().add(descriptionLabel);

        TextField descriptionTextField = new TextField();
        descriptionTextField.setPromptText("Enter task description");
        GridPane.setConstraints(descriptionTextField, 1, 0);
        grid.getChildren().add(descriptionTextField);

        Label timeLabel = new Label("Time:");
        GridPane.setConstraints(timeLabel, 0, 1);
        grid.getChildren().add(timeLabel);

        DatePicker datePicker = new DatePicker();
        GridPane.setConstraints(datePicker, 1, 1);
        grid.getChildren().add(datePicker);

        Label hours = new Label("Hours");
        GridPane.setConstraints(hours, 2, 2);
        grid.getChildren().add(hours);

        Label minutes = new Label("Minutes");
        GridPane.setConstraints(minutes, 3, 2);
        grid.getChildren().add(minutes);

        Label seconds = new Label("Seconds");
        GridPane.setConstraints(seconds, 4, 2);
        grid.getChildren().add(seconds);

        Spinner<Integer> hourSpinner = new Spinner<>();
        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
        GridPane.setConstraints(hourSpinner, 2, 1);
        grid.getChildren().add(hourSpinner);

        Spinner<Integer> minuteSpinner = new Spinner<>();
        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        GridPane.setConstraints(minuteSpinner, 3, 1);
        grid.getChildren().add(minuteSpinner);

        Spinner<Integer> secondSpinner = new Spinner<>();
        secondSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        GridPane.setConstraints(secondSpinner, 4, 1);
        grid.getChildren().add(secondSpinner);

        Button addButton = new Button("Add");
        GridPane.setConstraints(addButton, 0, 3);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Add a new task to the list
                String description = descriptionTextField.getText();
                LocalDateTime time = LocalDateTime.of(datePicker.getValue(),
                        LocalDateTime.MIN.toLocalTime().plusHours(hourSpinner.getValue())
                                .plusMinutes(minuteSpinner.getValue())
                                .plusSeconds(secondSpinner.getValue()));
                Task task = new Task(description, time);
                taskList.add(task);
                updateListView();
            }
        });
        grid.getChildren().add(addButton);

        Button removeButton = new Button("Remove");
        GridPane.setConstraints(removeButton, 1, 3);
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Remove the selected task from the list
                int index = listView.getSelectionModel().getSelectedIndex();
                taskList.remove(index);
                updateListView();
            }
        });
        grid.getChildren().add(removeButton);

        // Create the clear all button
        Button clearAllButton = new Button("Clear all");
        GridPane.setConstraints(clearAllButton, 3, 3);
        clearAllButton.setOnAction(event -> {
            taskList.clear();
            updateListView();

        });
        grid.getChildren().add(clearAllButton);

        listView.setPrefWidth(400);
        listView.setPrefHeight(300);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(grid, listView);

        Scene scene = new Scene(vBox);

        primaryStage.setTitle("My Todo List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateListView() {
        // Update the ListView with the current task list
        listView.getItems().clear();
        for (Task task : taskList) {
            listView.getItems().add(task.getDescription() + " (" + task.getTime().format(formatter) + ")");
        }
    }
    public static void main(String[] args) {
        launch();
    }
}
//class Task {
//
//
//}


