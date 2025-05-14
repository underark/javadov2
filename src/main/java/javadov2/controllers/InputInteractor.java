package javadov2.controllers;

import javadov2.enums.InputStringType;
import javadov2.interfaces.Interactor;
import javadov2.objects.TaskInfo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InputInteractor implements Interactor {
    private final StringProperty titleProperty;
    private final StringProperty dateProperty;
    private final StringProperty descriptionProperty;
    private final StringProperty tagProperty;
    private final StringProperty tagSearchProperty;

    public InputInteractor() {
        titleProperty = new SimpleStringProperty();
        dateProperty = new SimpleStringProperty();
        descriptionProperty = new SimpleStringProperty();
        tagProperty = new SimpleStringProperty();
        tagSearchProperty = new SimpleStringProperty();
    }

    public TaskInfo getUserInput() {
        String title = getStringValue(InputStringType.title);
        String date = getStringValue(InputStringType.date);
        String description = getStringValue(InputStringType.description);
        String tag = getStringValue(InputStringType.tag);
        return new TaskInfo(title, date, description, tag);
    }

    public String getStringValue(InputStringType type) {
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

