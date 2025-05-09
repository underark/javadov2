package javadov2.service;

import javadov2.enums.*;
import javadov2.interfaces.Interactor;
import javadov2.interfaces.ViewPort;
import javadov2.objects.ResultInfo;
import javadov2.objects.Task;
import javadov2.objects.TaskNode;
import javadov2.utilities.LayoutSwitcher;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

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

    public void wireStaticButtonsAndInput() {
        wireSaveTask(buttons.get(LayoutType.input).get(TypeOfButton.save));
        wireSearchTag(buttons.get(LayoutType.filter).get(TypeOfButton.search));
        wireTextInput(inputs.get(LayoutType.input).get(InputFieldType.title), inputInteractor.getProperty(InputStringType.title));
        wireTextInput(inputs.get(LayoutType.input).get(InputFieldType.dueDate), inputInteractor.getProperty(InputStringType.date));
        wireTextInput(inputs.get(LayoutType.input).get(InputFieldType.description), inputInteractor.getProperty(InputStringType.description));
        wireTextInput(inputs.get(LayoutType.input).get(InputFieldType.tagInput), inputInteractor.getProperty(InputStringType.tag));
        wireTextInput(inputs.get(LayoutType.edit).get(InputFieldType.title), inputInteractor.getProperty(InputStringType.title));
        wireTextInput(inputs.get(LayoutType.edit).get(InputFieldType.dueDate), inputInteractor.getProperty(InputStringType.date));
        wireTextInput(inputs.get(LayoutType.edit).get(InputFieldType.description), inputInteractor.getProperty(InputStringType.description));
        wireTextInput(inputs.get(LayoutType.edit).get(InputFieldType.tagInput), inputInteractor.getProperty(InputStringType.tag));
        wireTextInput(inputs.get(LayoutType.filter).get(InputFieldType.tagSearch), inputInteractor.getProperty(InputStringType.tagSearch));
    }

    private void wireTextInput(TextInputControl input, StringProperty stringProperty) {
        input.textProperty().bindBidirectional(stringProperty);
    }

    private void wireSaveTask(ButtonBase button) {
        button.setOnAction(event -> {
            ResultInfo result = inputInteractor.createTaskFromInput();
            if (result.task() != null) {
                layoutSwitcher.switchLayout(LayoutType.todo);
                viewController.addToDisplay(LayoutType.todo, result.task());
                TaskNode addedTask = viewController.getShownTask(result.task());
                wireCompleteButton(addedTask.getButton(TypeOfButton.complete), result.task());
                wireEditMenuButton(addedTask.getButton(TypeOfButton.editMenu), result.task());
                viewController.displayToast(result.message());
            } else {
                viewController.displayToast(result.message());
            }
        });
    }

    private void wireSearchTag(ButtonBase button) {
        button.setOnAction(event -> {
            ArrayList<Task> foundTasks = inputInteractor.searchTag();
            viewController.addToDisplay(LayoutType.filterDisplay, foundTasks);
            layoutSwitcher.switchLayout(LayoutType.filterDisplay);
        });
    }

    private void wireCompleteButton(ButtonBase button, Task task) {
        button.setOnAction(event -> {
            task.changeCompleted(!task.getCompletion());
            if (task.getCompletion()) {
                viewController.addToDisplay(LayoutType.complete, task);
                viewController.removeFromDisplay(LayoutType.todo, task);
            } else {
                viewController.addToDisplay(LayoutType.todo, task);
                viewController.removeFromDisplay(LayoutType.complete, task);
            }
        });
    }

    private void wireEditMenuButton(ButtonBase button, Task task) {
        button.setOnAction(event -> {
            layoutSwitcher.switchLayout(LayoutType.edit);
            inputInteractor.getProperty(InputStringType.title).setValue(task.getTitle());
            inputInteractor.getProperty(InputStringType.date).setValue(task.getDueDate());
            inputInteractor.getProperty(InputStringType.description).setValue(task.getDescription());
            inputInteractor.getProperty(InputStringType.tag).setValue(task.getTag());
            wireEditButton(buttons.get(LayoutType.edit).get(TypeOfButton.edit), task);
        });
    }

    private void wireEditButton(ButtonBase button, Task task) {
        button.setOnAction(event -> {
            Task editedTask = inputInteractor.editTask(task);
            viewController.removeFromDisplay(task);
            viewController.addToDisplay(LayoutType.todo, editedTask);
            task.changeCompleted(false);
            layoutSwitcher.switchLayout(LayoutType.todo);
        });
    }
}
