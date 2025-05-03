package javadov2.objects;

import javadov2.enums.InputType;
import javadov2.enums.TypeOfButton;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Control;
import javafx.scene.layout.GridPane;

import java.util.Map;

public class LayoutBundle {
    private final Node panel;
    private final GridPane display;
    private final Map<TypeOfButton, ButtonBase> buttons;
    private final Map<InputType, Control> inputs;

    public LayoutBundle(Node panel, Map<TypeOfButton, ButtonBase> buttons, Map<InputType, Control> inputs, GridPane display) {
        this.panel = panel;
        this.buttons = buttons;
        this.inputs = inputs;
        this.display = display;
    }

    public Node getPanel() {
        return panel;
    }

    public Map<InputType, Control> getInputs() {
        return inputs;
    }

    public Map<TypeOfButton, ButtonBase> getButtons() {
        return buttons;
    }

    public GridPane getDisplay() {
        return display;
    }
}
