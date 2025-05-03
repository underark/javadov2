package javadov2.builders;

import javadov2.enums.InputType;
import javadov2.enums.LayoutType;
import javadov2.enums.TypeOfButton;
import javadov2.interfaces.Builder;
import javadov2.objects.LayoutBundle;
import javadov2.utilities.BuilderUtility;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.EnumMap;
import java.util.Map;

// Makes the Layouts that we are going to store in our LayoutManager
    public class LayoutBuilder implements Builder {
        private StackPane root;
        private BuilderUtility builderUtility;

        public LayoutBuilder() {
            root = new StackPane();
            builderUtility = new BuilderUtility();
        }

    public Map<LayoutType, LayoutBundle> build() {
        Map<LayoutType, LayoutBundle> map = new EnumMap<>(LayoutType.class);
        map.put(LayoutType.todo, toDo());
        map.put(LayoutType.input, input());
        map.put(LayoutType.overdue, overdue());
        map.put(LayoutType.complete, complete());
        return map;
    }

    public StackPane giveRoot() {
        return root;
    }

    private LayoutBundle toDo() {
        GridPane gridPane = new GridPane();
        BorderPane borderPane = new BorderPane();

        Button homeButton = builderUtility.makeStylizedButton("Home", "menu-btn", "home");
        Button inputButton = builderUtility.makeStylizedButton("New task", "menu-btn", "input");
        Button overdueButton = builderUtility.makeStylizedButton("Show overdue", "menu-btn", "overdue");
        Button completeButton = builderUtility.makeStylizedButton("Show completed", "menu-btn", "complete");
        VBox container = new VBox(10, homeButton, inputButton, overdueButton, completeButton);
        container.getStyleClass().add("menu-box");
        Map<TypeOfButton, ButtonBase> buttons = Map.of(
                TypeOfButton.home, homeButton,
                TypeOfButton.input, inputButton,
                TypeOfButton.overdue, overdueButton,
                TypeOfButton.complete, completeButton
        );

        borderPane.setLeft(container);
        borderPane.setCenter(gridPane);
        return new LayoutBundle(borderPane, buttons, Map.of(), gridPane);
    }

    private LayoutBundle input() {
        GridPane inputContainer = new GridPane();
        TextField title = builderUtility.makeShortInput("title");
        TextField due = builderUtility.makeShortInput("due");
        TextArea description =  builderUtility.makeLongInput(100, "description");
        inputContainer.addRow(0, builderUtility.makeStylizedLabel("Title", "strong"), title);
        inputContainer.addRow(1, builderUtility.makeStylizedLabel("Due date", "strong"), due);
        inputContainer.addRow(2, builderUtility.makeStylizedLabel("Description", "strong"), description);
        Map<InputType, Control> inputs = Map.of(
                InputType.title, title,
                InputType.dueDate, due,
                InputType.description, description
        );

        Button homeButton = builderUtility.makeStylizedButton("Home", "menu-btn", "home");
        Button inputButton = builderUtility.makeStylizedButton("New task", "menu-btn", "input");
        Button overdueButton = builderUtility.makeStylizedButton("Show overdue", "menu-btn", "overdue");
        Button completeButton = builderUtility.makeStylizedButton("Show completed", "menu-btn", "complete");
        VBox container = new VBox(10, homeButton, inputButton, overdueButton, completeButton);
        container.getStyleClass().add("menu-box");
        Map<TypeOfButton, ButtonBase> buttons = Map.of(
                TypeOfButton.home, homeButton,
                TypeOfButton.input, inputButton,
                TypeOfButton.overdue, overdueButton,
                TypeOfButton.complete, completeButton
        );

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(container);
        borderPane.setCenter(inputContainer);
        return new LayoutBundle(borderPane, buttons, inputs, inputContainer);
    }

    private LayoutBundle overdue() {
        GridPane gridPane = new GridPane();
        BorderPane borderPane = new BorderPane();

        Button homeButton = builderUtility.makeStylizedButton("Home", "menu-btn", "home");
        Button inputButton = builderUtility.makeStylizedButton("New task", "menu-btn", "input");
        Button overdueButton = builderUtility.makeStylizedButton("Show overdue", "menu-btn", "overdue");
        Button completeButton = builderUtility.makeStylizedButton("Show completed", "menu-btn", "complete");
        VBox container = new VBox(10, homeButton, inputButton, overdueButton, completeButton);
        container.getStyleClass().add("menu-box");
        Map<TypeOfButton, ButtonBase> buttons = Map.of(
                TypeOfButton.home, homeButton,
                TypeOfButton.input, inputButton,
                TypeOfButton.overdue, overdueButton,
                TypeOfButton.complete, completeButton
        );

        borderPane.setLeft(container);
        borderPane.setCenter(gridPane);
        return new LayoutBundle(borderPane, buttons, Map.of(), gridPane);
    }

    private LayoutBundle complete() {
        GridPane gridPane = new GridPane();
        BorderPane borderPane = new BorderPane();

        Button homeButton = builderUtility.makeStylizedButton("Home", "menu-btn", "home");
        Button inputButton = builderUtility.makeStylizedButton("New task", "menu-btn", "input");
        Button overdueButton = builderUtility.makeStylizedButton("Show overdue", "menu-btn", "overdue");
        Button completeButton = builderUtility.makeStylizedButton("Show completed", "menu-btn", "complete");
        VBox container = new VBox(10, homeButton, inputButton, overdueButton, completeButton);
        container.getStyleClass().add("menu-box");
        Map<TypeOfButton, ButtonBase> buttons = Map.of(
                TypeOfButton.home, homeButton,
                TypeOfButton.input, inputButton,
                TypeOfButton.overdue, overdueButton,
                TypeOfButton.complete, completeButton
        );

        borderPane.setLeft(container);
        borderPane.setCenter(gridPane);
        return new LayoutBundle(borderPane, buttons, Map.of(), gridPane);
    }

    private Node toastContainer() {
        VBox container = new VBox();
        container.setAlignment(Pos.BOTTOM_RIGHT);
        container.setSpacing(15);
        container.setPadding(new Insets(10));
        container.setMaxHeight(300);
        container.setMaxWidth(300);
        container.getStyleClass().add("toast-container");
        container.setId("toast");
        return container;
    }
}
