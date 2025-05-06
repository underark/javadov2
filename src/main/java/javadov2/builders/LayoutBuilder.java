package javadov2.builders;

import javadov2.enums.ComboBoxType;
import javadov2.enums.InputFieldType;
import javadov2.enums.LayoutType;
import javadov2.enums.TypeOfButton;
import javadov2.interfaces.Builder;
import javadov2.objects.ButtonBundle;
import javadov2.objects.InputBundle;
import javadov2.objects.LayoutBundle;
import javadov2.utilities.BuilderUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.EnumMap;
import java.util.HashMap;
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
        map.put(LayoutType.filter, filter());
        return map;
    }

    public StackPane giveRoot() {
        return root;
    }

    private LayoutBundle toDo() {
        GridPane gridPane = new GridPane();
        BorderPane borderPane = new BorderPane();
        ButtonBundle buttonBundle = leftMenuPanel();
        borderPane.setLeft(buttonBundle.getContainer());
        borderPane.setCenter(gridPane);
        return new LayoutBundle(borderPane, Map.of(), buttonBundle.getButtons(), Map.of(), gridPane);
    }

    private LayoutBundle input() {
        GridPane inputContainer = new GridPane();
        InputBundle inputBundle = inputPanel();
        inputContainer.addRow(0, builderUtility.makeStylizedLabel("Title", "strong"), inputBundle.findInputByType(InputFieldType.title));
        inputContainer.addRow(1, builderUtility.makeStylizedLabel("Due date", "strong"), inputBundle.findInputByType(InputFieldType.dueDate));
        inputContainer.addRow(2, builderUtility.makeStylizedLabel("Description", "strong"), inputBundle.findInputByType(InputFieldType.description));

        Button saveButton = builderUtility.makeStylizedButton("Save", "form-btn", "save");
        HBox bottom = new HBox(saveButton);

        ButtonBundle buttonBundle = leftMenuPanel();
        buttonBundle.addButton(TypeOfButton.save, saveButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(buttonBundle.getContainer());
        borderPane.setCenter(inputContainer);
        borderPane.setBottom(bottom);
        return new LayoutBundle(borderPane, Map.of(), buttonBundle.getButtons(), inputBundle.getInputs(), inputContainer);
    }

    private LayoutBundle filter() {
        GridPane container = new GridPane();
        ButtonBundle buttonBundle = leftMenuPanel();
        ObservableList<String> options = FXCollections.observableArrayList("Complete", "Overdue", "Tag");
        ComboBox filterOptions = new ComboBox<>(options);
        Button searchButton = builderUtility.makeStylizedButton("search", "form-btn", "search");
        buttonBundle.addButton(TypeOfButton.search, searchButton);
        HBox center = new HBox(filterOptions, searchButton);
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(buttonBundle.getContainer());
        borderPane.setCenter(center);
        return new LayoutBundle(borderPane, Map.of(ComboBoxType.query, filterOptions), buttonBundle.getButtons(), Map.of(), container);
    }

    private LayoutBundle overdue() {
        GridPane gridPane = new GridPane();
        BorderPane borderPane = new BorderPane();
        ButtonBundle bundle = leftMenuPanel();
        borderPane.setLeft(bundle.getContainer());
        borderPane.setCenter(gridPane);
        return new LayoutBundle(borderPane, Map.of(), bundle.getButtons(), Map.of(), gridPane);
    }

    private LayoutBundle complete() {
        GridPane gridPane = new GridPane();
        BorderPane borderPane = new BorderPane();
        ButtonBundle menuButtons = leftMenuPanel();
        borderPane.setLeft(menuButtons.getContainer());
        borderPane.setCenter(gridPane);
        return new LayoutBundle(borderPane, Map.of(), menuButtons.getButtons(), Map.of(), gridPane);
    }

    private ButtonBundle leftMenuPanel() {
        Button homeButton = builderUtility.makeStylizedButton("Home", "menu-btn", "homeMenu");
        Button inputButton = builderUtility.makeStylizedButton("New task", "menu-btn", "input");
        Button filterButton = builderUtility.makeStylizedButton("Show filters", "menu-btn", "filterMenu");
        VBox container = new VBox();
        container.getStyleClass().add("menu-box");
        Map<TypeOfButton, ButtonBase> buttons = new HashMap<>(Map.of(
                TypeOfButton.homeMenu, homeButton,
                TypeOfButton.inputMenu, inputButton,
                TypeOfButton.filterMenu, filterButton
        ));
        return new ButtonBundle(buttons, container);
    }

    private InputBundle inputPanel() {
        TextField title = builderUtility.makeShortInput("title");
        TextField due = builderUtility.makeShortInput("due");
        TextArea description =  builderUtility.makeLongInput(100, "description");
        Map<InputFieldType, TextInputControl> inputs = new HashMap<>(Map.of(
                InputFieldType.title, title,
                InputFieldType.dueDate, due,
                InputFieldType.description, description
        ));
        return new InputBundle(inputs, new HBox());
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
