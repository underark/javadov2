package javadov2.utilities;

import javadov2.enums.LayoutType;
import javadov2.enums.TypeOfButton;
import javadov2.objects.LayoutBundle;
import javafx.geometry.Pos;
import javafx.scene.Node;

import javafx.scene.control.ButtonBase;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Map;

public class LayoutSwitcher {
    private final StackPane root;
    private final Map<LayoutType, LayoutBundle> layouts;
    private final VBox toastContainer;

    public LayoutSwitcher(StackPane root, VBox toastContainer, Map<LayoutType, LayoutBundle> layouts) {
        this.root = root;
        this.toastContainer = toastContainer;
        this.layouts = layouts;
        initialize();
    }

    public void switchLayout(LayoutType type) {
        Node layout = layouts.get(type).getPanel();
        root.getChildren().setAll(layout, toastContainer);
        root.setAlignment(toastContainer, Pos.BOTTOM_RIGHT);
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
                TypeOfButton.overdue, LayoutType.overdue,
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
