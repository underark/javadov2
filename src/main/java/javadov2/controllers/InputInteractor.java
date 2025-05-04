package javadov2.controllers;

import javadov2.enums.InputStringType;
import javadov2.interfaces.Interactor;
import javadov2.objects.Task;
import javadov2.service.TaskService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InputInteractor implements Interactor {
    private TaskService taskService;
    private final StringProperty titleProperty;
    private final StringProperty dateProperty;
    private final StringProperty descriptionProperty;

    public InputInteractor() {
        taskService = new TaskService(new TaskController());
        titleProperty = new SimpleStringProperty();
        dateProperty = new SimpleStringProperty();
        descriptionProperty = new SimpleStringProperty();
    }

    public void createTaskFromInput() {
        String title = getStringValue(InputStringType.title);
        String date = getStringValue(InputStringType.date);
        String description = getStringValue(InputStringType.description);
        String result = taskService.saveTask(new Task(title, date, description));
        System.out.println(result);
    }

    private String getStringValue(InputStringType type) {
        switch (type) {
            case title -> {
                return titleProperty.getValue();
            }
            case date -> {
                return dateProperty.getValue();
            }
            case description -> {
                return descriptionProperty.getValue();
            }
            default -> {
                return null;
            }
        }
    }

    private StringProperty getProperty(InputStringType type) {
        switch (type) {
            case title -> {
                return titleProperty;
            }
            case date -> {
                return dateProperty;
            }
            case description -> {
                return descriptionProperty;
            }
            default -> {
                return null;
            }
        }
    }
}

