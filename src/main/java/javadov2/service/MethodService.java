package javadov2.service;

import javadov2.enums.*;
import javadov2.interfaces.Interactor;
import javadov2.interfaces.ViewPort;
import javadov2.objects.Task;
import javadov2.utilities.LayoutSwitcher;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Map;

public class MethodService {
    private final Map<LayoutType, Map<TypeOfButton, ButtonBase>> buttons;
    private final Map<LayoutType, Map<InputFieldType, TextInputControl>> inputs;
    private ViewPort viewController;
    private Interactor inputInteractor;
    private LayoutSwitcher layoutSwitcher;

    public MethodService(Map<LayoutType, Map<TypeOfButton, ButtonBase>> buttons, Map<LayoutType, Map<InputFieldType, TextInputControl>> inputs, LayoutSwitcher layoutSwitcher, ViewPort viewController, Interactor inputInteractor) {
        this.layoutSwitcher = layoutSwitcher;
        this.buttons = buttons;
        this.inputs = inputs;
        this.viewController = viewController;
        this.inputInteractor = inputInteractor;
    }

    public void setUpStatic() {
        wireSaveTask(buttons.get(LayoutType.input).get(TypeOfButton.save));
        wireSearchTag(buttons.get(LayoutType.filter).get(TypeOfButton.search));
        wireTextInput(inputs.get(LayoutType.input).get(InputFieldType.title), inputInteractor.getProperty(InputStringType.title));
        wireTextInput(inputs.get(LayoutType.input).get(InputFieldType.dueDate), inputInteractor.getProperty(InputStringType.date));
        wireTextInput(inputs.get(LayoutType.input).get(InputFieldType.description), inputInteractor.getProperty(InputStringType.description));
        wireTextInput(inputs.get(LayoutType.input).get(InputFieldType.tagInput), inputInteractor.getProperty(InputStringType.tag));
        wireTextInput(inputs.get(LayoutType.filter).get(InputFieldType.tagSearch), inputInteractor.getProperty(InputStringType.tagSearch));
    }

    private void wireTextInput(TextInputControl input, StringProperty stringProperty) {
        input.textProperty().bindBidirectional(stringProperty);
    }

    private void wireSaveTask(ButtonBase button) {
        button.setOnAction(event -> {
            Task task = inputInteractor.createTaskFromInput();
            viewController.addToDisplay(LayoutType.todo, task);
        });
    }

    private void wireSearchTag(ButtonBase button) {
        button.setOnAction(event -> {
            ArrayList<Task> foundTasks = inputInteractor.searchTag();
            viewController.addToDisplay(LayoutType.filterDisplay, foundTasks);
            layoutSwitcher.switchLayout(LayoutType.filterDisplay);
        });
    }
}
