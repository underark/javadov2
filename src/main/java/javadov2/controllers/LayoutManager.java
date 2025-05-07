package javadov2.controllers;

import javadov2.enums.ComboBoxType;
import javadov2.enums.InputFieldType;
import javadov2.enums.LayoutType;
import javadov2.enums.TypeOfButton;
import javadov2.interfaces.Builder;
import javadov2.interfaces.Manager;
import javadov2.objects.LayoutBundle;
import javadov2.utilities.LayoutSwitcher;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class LayoutManager implements Manager {
    private Builder layoutBuilder;
    public LayoutSwitcher layoutSwitcher;
    private StackPane root;
    private Map<LayoutType, LayoutBundle> layouts;

    public LayoutManager(Builder layoutBuilder) {
        this.layoutBuilder = layoutBuilder;
    }

    // Consider removing this method entirely to the constructor and removing reference to the layoutBuilder?
    public void initialize() {
        root = layoutBuilder.giveRoot();
        layouts = layoutBuilder.build();
        layoutSwitcher = new LayoutSwitcher(root, layouts);
    }

    public StackPane giveRoot() {
        return root;
    }

    public LayoutSwitcher giveLayoutSwitcher() {
        return layoutSwitcher;
    }

    public Map<LayoutType, LayoutBundle> giveLayouts() {
        return layouts;
    }

    public ArrayList<ButtonBase> findButtonsByType(TypeOfButton buttonType) {
        ArrayList<ButtonBase> foundButtons = new ArrayList<>();
        layouts.forEach((type, bundle) -> {
            Map<TypeOfButton, ButtonBase> buttons = bundle.getButtons();
            if (buttons.containsKey(buttonType)) {
                ButtonBase foundButton = buttons.get(buttonType);
                foundButtons.add(foundButton);
            }
        });
        return foundButtons;
    }

    public ArrayList<TextInputControl> findInputsByType(InputFieldType inputType) {
        ArrayList<TextInputControl> foundInputs = new ArrayList<>();
        layouts.forEach((type, bundle) -> {
            Map<InputFieldType, TextInputControl> inputs = bundle.getInputs();
            if (inputs.containsKey(inputType)) {
                TextInputControl foundInput = inputs.get(inputType);
                foundInputs.add(foundInput);
            }
        });
        return foundInputs;
    }

    public Map<LayoutType, GridPane> getDisplays() {
        Map<LayoutType, GridPane> map = new EnumMap<>(LayoutType.class);
        layouts.forEach((type, layout) -> map.put(type, layout.getDisplay()));
        return map;
    }
}
