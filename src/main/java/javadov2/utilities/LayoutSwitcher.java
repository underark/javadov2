package javadov2.utilities;

import javadov2.enums.LayoutType;
import javadov2.enums.TypeOfButton;
import javadov2.objects.LayoutBundle;
import javafx.scene.Node;

import javafx.scene.control.ButtonBase;
import javafx.scene.layout.StackPane;

import java.util.Map;

public class LayoutSwitcher {
    private final StackPane root;
    private final Map<LayoutType, LayoutBundle> layouts;

    public LayoutSwitcher(StackPane root, Map<LayoutType, LayoutBundle> layouts) {
        this.root = root;
        this.layouts = layouts;
        initialize();
    }

    public void switchLayout(LayoutType type) {
        Node layout = layouts.get(type).getPanel();
        root.getChildren().setAll(layout);
    }

    private void initialize() {
        wireMenuButtons();
        switchLayout(LayoutType.todo);
    }

    private void wireMenuButtons() {
        layouts.forEach((type, layoutBundle) -> {
            matchButtonsToLayout(layoutBundle.getButtons());
        });
    }

    private void matchButtonsToLayout(Map<TypeOfButton, ButtonBase> buttonMap) {
        Map<TypeOfButton, LayoutType> map = Map.of(
                TypeOfButton.homeMenu, LayoutType.todo,
                TypeOfButton.inputMenu, LayoutType.input,
                TypeOfButton.complete, LayoutType.complete,
                TypeOfButton.filter, LayoutType.filter
        );

        buttonMap.forEach((typeOfButton, buttonBase) -> {
            if (map.containsKey(typeOfButton)) {
                buttonBase.setOnAction(event -> switchLayout(map.get(typeOfButton)));
            }
        });
    }
}
