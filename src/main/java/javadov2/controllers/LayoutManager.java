package javadov2.controllers;

import javadov2.enums.ComboBoxType;
import javadov2.enums.InputFieldType;
import javadov2.enums.LayoutType;
import javadov2.enums.TypeOfButton;
import javadov2.interfaces.Builder;
import javadov2.interfaces.Manager;
import javadov2.objects.LayoutBundle;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.EnumMap;
import java.util.Map;

public class LayoutManager implements Manager {
    private final StackPane root;
    private final VBox toastContainer;
    private final Map<LayoutType, LayoutBundle> layouts;

    public LayoutManager(Builder layoutBuilder) {
        root = layoutBuilder.giveRoot();
        toastContainer = layoutBuilder.giveToastContainer();
        layouts = layoutBuilder.build();
    }

    public StackPane giveRoot() {
        return root;
    }

    public VBox giveToastContainer() {
        return toastContainer;
    }

    public Map<LayoutType, LayoutBundle> giveLayouts() {
        return layouts;
    }

    public Map<LayoutType, VBox> giveDisplays() {
        Map<LayoutType, VBox> map = new EnumMap<>(LayoutType.class);
        layouts.forEach((type, layout) -> map.put(type, layout.getDisplay()));
        return map;
    }

    public Map<LayoutType, Map<TypeOfButton, ButtonBase>> giveButtons() {
        Map<LayoutType, Map<TypeOfButton, ButtonBase>> buttons = new EnumMap<>(LayoutType.class);
        layouts.forEach((layout, bundle) -> buttons.put(layout, bundle.getButtons()));
        return buttons;
    }

    public Map<LayoutType, Map<InputFieldType, TextInputControl>> giveInputs() {
        Map<LayoutType, Map<InputFieldType, TextInputControl>> inputs = new EnumMap<>(LayoutType.class);
        layouts.forEach((layout, bundle) -> inputs.put(layout, bundle.getInputs()));
        return inputs;
    }

    public Map<LayoutType, Map<ComboBoxType, ComboBoxBase>> giveComboBoxes() {
        Map<LayoutType, Map<ComboBoxType, ComboBoxBase>> boxes = new EnumMap<>(LayoutType.class);
        layouts.forEach((layout, bundle) -> boxes.put(layout, bundle.getComboBoxes()));
        return boxes;
    }
}
