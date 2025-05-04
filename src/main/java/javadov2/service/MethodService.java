package javadov2.service;

import javadov2.enums.*;
import javadov2.interfaces.Interactor;
import javadov2.interfaces.ViewPort;
import javadov2.objects.Task;
import javadov2.objects.TaskNode;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.*;

import java.util.ArrayList;

public class MethodService {
    private ArrayList<ButtonBase> saveButtons;
    private ArrayList<TextInputControl> titleInputs;
    private ArrayList<TextInputControl> dueDateInputs;
    private ArrayList<TextInputControl> descriptionInputs;
    private ViewPort viewController;
    private Interactor inputInteractor;

    public MethodService(ArrayList<ButtonBase> saveButtons, ArrayList<TextInputControl> titleInputs, ArrayList<TextInputControl> dueDateInputs, ArrayList<TextInputControl> descriptionInputs, ViewPort viewController, Interactor inputInteractor) {
        this.saveButtons = saveButtons;
        this.titleInputs = titleInputs;
        this.dueDateInputs = dueDateInputs;
        this.descriptionInputs = descriptionInputs;
        this.viewController = viewController;
        this.inputInteractor = inputInteractor;
    }

    public void setUpStatic() {
        wireButtons(TypeOfButton.save);
        wireTextInputs(InputFieldType.title, inputInteractor.getProperty(InputStringType.title));
        wireTextInputs(InputFieldType.dueDate, inputInteractor.getProperty(InputStringType.date));
        wireTextInputs(InputFieldType.description, inputInteractor.getProperty(InputStringType.description));
    }

    private void wireTextInputs(InputFieldType fieldType, StringProperty stringProperty) {
        switch (fieldType) {
            case title -> {
                titleInputs.forEach(input -> input.textProperty().bindBidirectional(stringProperty));
            }
            case dueDate -> {
                dueDateInputs.forEach(input -> input.textProperty().bindBidirectional(stringProperty));
            }
            case description -> {
                descriptionInputs.forEach(input -> input.textProperty().bindBidirectional(stringProperty));
            }
        }
    }

    private void wireButtons(TypeOfButton typeOfButton) {
        switch (typeOfButton) {
            case save -> {
                saveButtons.forEach(button -> button.setOnAction
                        (e -> {
                            Task result = inputInteractor.createTaskFromInput();
                            viewController.updateDisplay(UpdateType.add, result);
                        }));
            }
        }
    }
}
