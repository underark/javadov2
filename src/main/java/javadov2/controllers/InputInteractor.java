package javadov2.controllers;

import javadov2.enums.InputStringType;
import javadov2.interfaces.Interactor;
import javadov2.objects.Task;
import javadov2.objects.TaskInfo;
import javadov2.service.TaskService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;


public class InputInteractor implements Interactor {
    private TaskService taskService;
    private final StringProperty titleProperty;
    private final StringProperty dateProperty;
    private final StringProperty descriptionProperty;
    private final StringProperty tagProperty;
    private final StringProperty tagSearchProperty;

    public InputInteractor() {
        taskService = new TaskService(new TaskController());
        titleProperty = new SimpleStringProperty();
        dateProperty = new SimpleStringProperty();
        descriptionProperty = new SimpleStringProperty();
        tagProperty = new SimpleStringProperty();
        tagSearchProperty = new SimpleStringProperty();
    }

    public Task createTaskFromInput() {
        String title = getStringValue(InputStringType.title);
        String date = getStringValue(InputStringType.date);
        String description = getStringValue(InputStringType.description);
        String tag = getStringValue(InputStringType.tag);
        Task task = new Task(title, date, description, tag);
        System.out.println(tag);
        String result = taskService.saveTask(task);
        return task;
    }

    public Task editTask(Task task) {
        return taskService.editTask(task, new TaskInfo(titleProperty.getValue(), dateProperty.getValue(), descriptionProperty.getValue(), tagProperty.getValue()));
    }

    public void markTaskCompleted(Task task) {
        taskService.markCompleted(task);
    }

    public ArrayList<Task> searchTag() {
        return taskService.searchTags(getStringValue(InputStringType.tagSearch));
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
            case tag -> {
                return tagProperty.getValue();
            }
            case tagSearch -> {
                return tagSearchProperty.getValue();
            }
            default -> {
                return null;
            }
        }
    }

    public StringProperty getProperty(InputStringType type) {
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
            case tag -> {
                return tagProperty;
            }
            case tagSearch -> {
                return tagSearchProperty;
            }
            default -> {
                return null;
            }
        }
    }
}

