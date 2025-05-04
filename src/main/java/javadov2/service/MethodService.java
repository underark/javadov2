package javadov2.service;

import javadov2.enums.InputFieldType;
import javadov2.enums.LayoutType;
import javadov2.enums.TypeOfButton;
import javadov2.interfaces.Interactor;
import javadov2.interfaces.ViewPort;
import javadov2.objects.LayoutBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;

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
    }

    private void wireTextInputs(InputFieldType fieldType) {
        switch (fieldType) {
            case title ->
        }
    }

    private void wireButtons(TypeOfButton typeOfButton) {
        switch (typeOfButton) {
            case save -> {
                    ArrayList<ButtonBase> saveButtons = findButtonsByType(TypeOfButton.save, layouts);
                    saveButtons.forEach(button -> button.setOnAction(e -> inputInteractor.createTaskFromInput()));
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
}
