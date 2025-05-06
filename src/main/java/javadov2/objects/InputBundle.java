package javadov2.objects;

import javadov2.enums.InputFieldType;
import javafx.scene.Node;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Pane;

import java.util.Map;

public class InputBundle {
    Map<InputFieldType, TextInputControl> inputs;
    Node container;

    public InputBundle(Map<InputFieldType, TextInputControl> map, Pane container) {
        inputs = map;
        inputs.forEach((type, input) -> container.getChildren().add(input));
        this.container = container;
    }

    public Node getContainer() {
        return container;
    }

    public Map<InputFieldType, TextInputControl> getInputs() {
        return inputs;
    }

    public TextInputControl findInputByType(InputFieldType type) {
        return inputs.getOrDefault(type, null);
    }
}
