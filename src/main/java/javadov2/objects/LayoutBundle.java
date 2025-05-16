package javadov2.objects;

import javadov2.enums.ComboBoxType;
import javadov2.enums.InputFieldType;
import javadov2.enums.TypeOfButton;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.VBox;

import java.util.Map;

public class LayoutBundle {
    private final Node panel;
    private final VBox display;
    private final Map<TypeOfButton, ButtonBase> buttons;
    private final Map<InputFieldType, TextInputControl> inputs;
    private final Map<ComboBoxType, ComboBoxBase> comboBoxes;

    public LayoutBundle(Node panel, Map<TypeOfButton, ButtonBase> buttons, Map<InputFieldType, TextInputControl> inputs, VBox display, Map<ComboBoxType, ComboBoxBase> comboBoxes) {
        this.panel = panel;
        this.buttons = buttons;
        this.inputs = inputs;
        this.display = display;
        this.comboBoxes = comboBoxes;
    }

    public Node getPanel() {
        return panel;
    }

    public Map<InputFieldType, TextInputControl> getInputs() {
        return inputs;
    }

    public Map<TypeOfButton, ButtonBase> getButtons() {
        return buttons;
    }

    public VBox getDisplay() {
        return display;
    }

    public Map<ComboBoxType, ComboBoxBase> getComboBoxes() {
        return comboBoxes;
    }

    public ComboBoxBase getComboBoxByType(ComboBoxType type) {
        return comboBoxes.get(type);
    }
}
