package javadov2.service;

import javadov2.enums.*;
import javadov2.interfaces.Interactor;
import javadov2.interfaces.ObjectService;
import javadov2.interfaces.ViewPort;
import javadov2.objects.ResultInfo;
import javadov2.objects.Task;
import javadov2.objects.TaskInfo;
import javadov2.objects.TaskNode;
import javadov2.utilities.LayoutSwitcher;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MethodService {
    private final Map<LayoutType, Map<TypeOfButton, ButtonBase>> buttons;
    private final Map<LayoutType, Map<InputFieldType, TextInputControl>> inputs;
    private ViewPort viewController;
    private ObjectService taskService;
    private Interactor inputInteractor;
    private LayoutSwitcher layoutSwitcher;

    public MethodService(Map<LayoutType, Map<TypeOfButton, ButtonBase>> buttons, Map<LayoutType, Map<InputFieldType, TextInputControl>> inputs, LayoutSwitcher layoutSwitcher, ViewPort viewController, Interactor inputInteractor, ObjectService taskService) {
        this.layoutSwitcher = layoutSwitcher;
        this.buttons = buttons;
        this.inputs = inputs;
        this.viewController = viewController;
        this.inputInteractor = inputInteractor;
        this.taskService = taskService;
    }

    public void wireExistingTaskButtons(Map<Task, TaskNode> tasks) {
        tasks.forEach((task, node) -> {
            Map<TypeOfButton, ButtonBase> buttons = node.getButtons();
            buttons.forEach((type, button) -> matchButtonToWiring(type, button, node.getTask()));
        });
    }

    // Make this into a pattern matching function?
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
            TaskInfo info = inputInteractor.getUserInput();
            ResultInfo result = taskService.saveTask(info);
            if (result.task() != null) {
                viewController.addToDisplay(LayoutType.todo, result.task());
                TaskNode addedTask = viewController.getShownTask(result.task());
                wireCompleteButton(addedTask.getButton(TypeOfButton.complete), result.task());
                wireEditMenuButton(addedTask.getButton(TypeOfButton.editMenu), result.task());
                layoutSwitcher.switchLayout(LayoutType.todo);
                viewController.displayToast(result.message());
            } else {
                viewController.displayToast(result.message());
            }
        });
    }

    private void wireSearchTag(ButtonBase button) {
        button.setOnAction(event -> {
            List<Task> foundTasks = new ArrayList<>();
            String term = inputInteractor.getStringValue(InputStringType.tagSearch);
            Object type = inputInteractor.getComboBoxValue(LayoutType.filter, ComboBoxType.filter);
            if (term == null || type == null) {
                viewController.displayToast("Search term and type must not be empty");
            } else {
                if (type.toString().equals("title")) {
                    foundTasks = taskService.searchTitle(term);
                }
                if (type.toString().equals("date")) {
                    foundTasks = taskService.searchDate(term);
                }
                if (type.toString().equals("tag")) {
                    foundTasks = taskService.searchTag(term);
                }

                if (foundTasks.isEmpty()) {
                    layoutSwitcher.switchLayout(LayoutType.emptyFilter);
                } else {
                    viewController.addToDisplay(LayoutType.filterDisplay, foundTasks);
                    layoutSwitcher.switchLayout(LayoutType.filterDisplay);
                }
            }
        });
    }

    private void wireCompleteButton(ButtonBase button, Task task) {
        button.setOnAction(event -> {
            task.changeCompleted(!task.getCompletion());
            ResultInfo result = taskService.changeCompletion(task);
            if (task.getCompletion()) {
                button.setText("Mark incomplete");
                viewController.addToDisplay(LayoutType.complete, task);
                viewController.removeFromDisplay(LayoutType.todo, task);
            } else {
                button.setText("Mark complete");
                viewController.addToDisplay(LayoutType.todo, task);
                viewController.removeFromDisplay(LayoutType.complete, task);
            }
            viewController.displayToast(result.message());
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
            ResultInfo resultInfo = taskService.editTask(task, inputInteractor.getUserInput());
            if (resultInfo.task() != null) {
                viewController.removeFromDisplay(task);
                TaskNode node = viewController.addToDisplay(LayoutType.todo, resultInfo.task());
                wireCompleteButton(node.getButton(TypeOfButton.complete), node.getTask());
                wireEditMenuButton(node.getButton(TypeOfButton.editMenu), node.getTask());
                layoutSwitcher.switchLayout(LayoutType.todo);
                viewController.displayToast(resultInfo.message());
            } else {
                viewController.displayToast(resultInfo.message());
            }
        });
    }

    private void matchButtonToWiring(TypeOfButton type, ButtonBase button, Task task) {
        switch (type) {
            case complete -> wireCompleteButton(button, task);
            case editMenu -> wireEditMenuButton(button, task);
        }
    }
}
