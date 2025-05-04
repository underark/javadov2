package javadov2.service;

import javadov2.enums.*;
import javadov2.interfaces.Interactor;
import javadov2.interfaces.ViewPort;
import javadov2.objects.LayoutBundle;
import javadov2.objects.Task;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Map;

public class MethodService {
    private Map<LayoutType, LayoutBundle> layouts;
    private ViewPort viewController;
    private Interactor inputInteractor;

    public MethodService(Map<LayoutType, LayoutBundle> layouts, ViewPort viewController, Interactor inputInteractor) {
        this.layouts = layouts;
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
        ArrayList<TextInputControl> titleInputs = findInputsByType(fieldType, layouts);
        titleInputs.forEach(input -> input.textProperty().bindBidirectional(stringProperty));
    }

    private void wireButtons(TypeOfButton typeOfButton) {
        switch (typeOfButton) {
            case save -> {
                    ArrayList<ButtonBase> saveButtons = findButtonsByType(TypeOfButton.save, layouts);
                    saveButtons.forEach(button -> button.setOnAction
                            (e -> {
                                GridPane display = layouts.get(LayoutType.todo).getDisplay();
                                Task result = inputInteractor.createTaskFromInput();
                                viewController.updateDisplay(display, UpdateType.add, result);
                            }));
            }
        }
    }

    private ArrayList<ButtonBase> findButtonsByType(TypeOfButton buttonType, Map<LayoutType, LayoutBundle> bundles) {
        ArrayList<ButtonBase> foundButtons = new ArrayList<>();
        bundles.forEach((type, bundle) -> {
            Map<TypeOfButton, ButtonBase> buttons = bundle.getButtons();
            if (buttons.containsKey(buttonType)) {
                ButtonBase foundButton = buttons.get(buttonType);
                foundButtons.add(foundButton);
            }
        });
        return foundButtons;
    }

    private ArrayList<TextInputControl> findInputsByType(InputFieldType inputType, Map<LayoutType, LayoutBundle> bundles) {
        ArrayList<TextInputControl> foundInputs = new ArrayList<>();
        bundles.forEach((type, bundle) -> {
            Map<InputFieldType, TextInputControl> inputs = bundle.getInputs();
            if (inputs.containsKey(inputType)) {
                TextInputControl foundInput = inputs.get(inputType);
                foundInputs.add(foundInput);
            }
        });
        return foundInputs;
    }
}
