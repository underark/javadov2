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

public class MethodService {
    // Streamline these lists to be maps of Layouts, Button types, and buttons.
    private ArrayList<ButtonBase> saveButtons;
    private ArrayList<ButtonBase> searchButtons;
    private ArrayList<TextInputControl> tagInputs;
    private ArrayList<TextInputControl> tagSearch;
    private ArrayList<TextInputControl> titleInputs;
    private ArrayList<TextInputControl> dueDateInputs;
    private ArrayList<TextInputControl> descriptionInputs;
    private ViewPort viewController;
    private Interactor inputInteractor;
    // Move the LayoutSwitcher here from LayoutManager and centralize wiring here
    private LayoutSwitcher layoutSwitcher;

    public MethodService(LayoutSwitcher switcher, ArrayList<ButtonBase> saveButtons, ArrayList<ButtonBase> searchButtons, ArrayList<TextInputControl> tagInputs, ArrayList<TextInputControl> tagSearch, ArrayList<TextInputControl> titleInputs, ArrayList<TextInputControl> dueDateInputs, ArrayList<TextInputControl> descriptionInputs, ViewPort viewController, Interactor inputInteractor) {
        layoutSwitcher = switcher;
        this.saveButtons = saveButtons;
        this.searchButtons = searchButtons;
        this.tagInputs = tagInputs;
        this.tagSearch = tagSearch;
        this.titleInputs = titleInputs;
        this.dueDateInputs = dueDateInputs;
        this.descriptionInputs = descriptionInputs;
        this.viewController = viewController;
        this.inputInteractor = inputInteractor;
    }

    public void setUpStatic() {
        wireButtons(TypeOfButton.save);
        wireButtons(TypeOfButton.search);
        wireTextInputs(InputFieldType.title, inputInteractor.getProperty(InputStringType.title));
        wireTextInputs(InputFieldType.dueDate, inputInteractor.getProperty(InputStringType.date));
        wireTextInputs(InputFieldType.description, inputInteractor.getProperty(InputStringType.description));
        wireTextInputs(InputFieldType.tagInput, inputInteractor.getProperty(InputStringType.tag));
        wireTextInputs(InputFieldType.tagSearch, inputInteractor.getProperty(InputStringType.tagSearch));
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
            case tagInput -> {
                tagInputs.forEach(input -> input.textProperty().bindBidirectional(stringProperty));
            }
            case tagSearch -> {
                tagSearch.forEach(input -> input.textProperty().bindBidirectional(stringProperty));
            }
        }
    }

    // Am I ever going to call this with something other than 'save'?
    // If not, consider renaming it to wireSaveButtons and make it do just that
    private void wireButtons(TypeOfButton typeOfButton) {
        switch (typeOfButton) {
            case save -> {
                saveButtons.forEach(button -> button.setOnAction(e -> {
                    Task newTask = inputInteractor.createTaskFromInput();
                    viewController.addToDisplay(LayoutType.todo, newTask);
                }));
            }
            case search -> {
                searchButtons.forEach(button -> button.setOnAction(event -> {
                    ArrayList<Task> foundTasks = inputInteractor.searchTag();
                    viewController.addToDisplay(LayoutType.filterDisplay, foundTasks);
                    layoutSwitcher.switchLayout(LayoutType.filterDisplay);
                }));
            }
        }
    }
}
