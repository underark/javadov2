package javadov2.objects;

import javadov2.enums.TypeOfButton;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.layout.Pane;

import java.util.Map;

public class ButtonBundle {
    Map<TypeOfButton, ButtonBase> buttons;
    Node container;

    public ButtonBundle(Map<TypeOfButton, ButtonBase> map, Pane container) {
        buttons = map;
        buttons.forEach((type, button) -> container.getChildren().add(button));
        this.container = container;
    }

    public Node getContainer() {
        return container;
    }

    public Map<TypeOfButton, ButtonBase> getButtons() {
        return buttons;
    }

    public void addButton(TypeOfButton type, ButtonBase button) {
        buttons.put(type, button);
    }
}
