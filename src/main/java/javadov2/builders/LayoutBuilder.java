package javadov2.builders;

import javadov2.enums.InputFieldType;
import javadov2.enums.LayoutType;
import javadov2.enums.TypeOfButton;
import javadov2.interfaces.Builder;
import javadov2.objects.ButtonBundle;
import javadov2.objects.InputBundle;
import javadov2.objects.LayoutBundle;
import javadov2.utilities.BuilderUtility;
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
        private final StackPane root;
        private final VBox toastContainer;
        private BuilderUtility builderUtility;

        public LayoutBuilder() {
            root = new StackPane();
            toastContainer = toastContainer();
            builderUtility = new BuilderUtility();
        }

    public Map<LayoutType, LayoutBundle> build() {
        Map<LayoutType, LayoutBundle> map = new EnumMap<>(LayoutType.class);
        map.put(LayoutType.todo, toDo());
        map.put(LayoutType.input, input());
        map.put(LayoutType.overdue, overdue());
        map.put(LayoutType.complete, complete());
        map.put(LayoutType.filter, filter());
        map.put(LayoutType.filterDisplay, filterDisplay());
        map.put(LayoutType.emptyFilter, emptyFilterDisplay());
        map.put(LayoutType.edit, edit());
        return map;
    }

    public StackPane giveRoot() {
        return root;
    }

    public VBox giveToastContainer() {
        return toastContainer;
    }

    private LayoutBundle toDo() {
        GridPane gridPane = new GridPane();
        BorderPane borderPane = new BorderPane();
        ButtonBundle buttonBundle = leftMenuPanel();
        borderPane.setLeft(buttonBundle.getContainer());
        borderPane.setCenter(gridPane);
        return new LayoutBundle(borderPane, buttonBundle.getButtons(), Map.of(), gridPane);
    }

    private LayoutBundle input() {
        GridPane inputContainer = new GridPane();
        InputBundle inputBundle = inputPanel();
        inputContainer.addRow(0, builderUtility.makeStylizedLabel("Title", "strong"), inputBundle.findInputByType(InputFieldType.title));
        inputContainer.addRow(1, builderUtility.makeStylizedLabel("Due date", "strong"), inputBundle.findInputByType(InputFieldType.dueDate));
        inputContainer.addRow(2, builderUtility.makeStylizedLabel("Description", "strong"), inputBundle.findInputByType(InputFieldType.description));
        inputContainer.addRow(3, builderUtility.makeStylizedLabel("Tag", "strong"), inputBundle.findInputByType(InputFieldType.tagInput));
        Button saveButton = builderUtility.makeStylizedButton("Save", "form-btn", "save");
        HBox bottom = new HBox(saveButton);

        ButtonBundle buttonBundle = leftMenuPanel();
        buttonBundle.addButton(TypeOfButton.save, saveButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(buttonBundle.getContainer());
        borderPane.setCenter(inputContainer);
        borderPane.setBottom(bottom);
        return new LayoutBundle(borderPane, buttonBundle.getButtons(), inputBundle.getInputs(), inputContainer);
    }

    private LayoutBundle edit() {
        GridPane inputContainer = new GridPane();
        InputBundle inputBundle = inputPanel();
        inputContainer.addRow(0, builderUtility.makeStylizedLabel("Title", "strong"), inputBundle.findInputByType(InputFieldType.title));
        inputContainer.addRow(1, builderUtility.makeStylizedLabel("Due date", "strong"), inputBundle.findInputByType(InputFieldType.dueDate));
        inputContainer.addRow(2, builderUtility.makeStylizedLabel("Description", "strong"), inputBundle.findInputByType(InputFieldType.description));
        inputContainer.addRow(3, builderUtility.makeStylizedLabel("Tag", "strong"), inputBundle.findInputByType(InputFieldType.tagInput));
        Button editButton = builderUtility.makeStylizedButton("Save", "form-btn", "save");
        HBox bottom = new HBox(editButton);

        ButtonBundle buttonBundle = leftMenuPanel();
        buttonBundle.addButton(TypeOfButton.edit, editButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(buttonBundle.getContainer());
        borderPane.setCenter(inputContainer);
        borderPane.setBottom(bottom);
        return new LayoutBundle(borderPane, buttonBundle.getButtons(), inputBundle.getInputs(), inputContainer);
    }

    private LayoutBundle filter() {
        GridPane container = new GridPane();
        ButtonBundle buttonBundle = leftMenuPanel();
        Button searchButton = builderUtility.makeStylizedButton("search", "form-btn", "search");
        buttonBundle.addButton(TypeOfButton.search, searchButton);
        InputBundle inputBundle = new InputBundle(Map.of(InputFieldType.tagSearch, builderUtility.makeShortInput("tagSearch")), new HBox());
        HBox center = new HBox(inputBundle.findInputByType(InputFieldType.tagSearch), searchButton);
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(buttonBundle.getContainer());
        borderPane.setCenter(center);
        return new LayoutBundle(borderPane, buttonBundle.getButtons(), inputBundle.getInputs(), container);
    }

    private LayoutBundle filterDisplay() {
        GridPane container = new GridPane();
        ButtonBundle buttonBundle = leftMenuPanel();
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(buttonBundle.getContainer());
        borderPane.setCenter(container);
        return new LayoutBundle(borderPane, buttonBundle.getButtons(), Map.of(), container);
    }

    private LayoutBundle emptyFilterDisplay() {
        ButtonBundle menu = leftMenuPanel();
        HBox center = new HBox(builderUtility.makeStylizedLabel("No tasks found!", "strong"));
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(menu.getContainer());
        borderPane.setCenter(center);
        return new LayoutBundle(borderPane, menu.getButtons(), Map.of(), new GridPane());
    }

    private LayoutBundle overdue() {
        GridPane gridPane = new GridPane();
        BorderPane borderPane = new BorderPane();
        ButtonBundle bundle = leftMenuPanel();
        borderPane.setLeft(bundle.getContainer());
        borderPane.setCenter(gridPane);
        return new LayoutBundle(borderPane, bundle.getButtons(), Map.of(), gridPane);
    }

    private LayoutBundle complete() {
        GridPane gridPane = new GridPane();
        BorderPane borderPane = new BorderPane();
        ButtonBundle menuButtons = leftMenuPanel();
        borderPane.setLeft(menuButtons.getContainer());
        borderPane.setCenter(gridPane);
        return new LayoutBundle(borderPane, menuButtons.getButtons(), Map.of(), gridPane);
    }

    private ButtonBundle leftMenuPanel() {
        Button homeButton = builderUtility.makeStylizedButton("Home", "menu-btn", "home");
        Button inputButton = builderUtility.makeStylizedButton("New task", "menu-btn", "input");
        Button completeButton = builderUtility.makeStylizedButton("Show complete", "menu-btn", "complete");
        Button filterButton = builderUtility.makeStylizedButton("Search tag", "menu-btn", "searchTag");
        Button overdueButton = builderUtility.makeStylizedButton("Show overdue", "menu-btn", "overdue");
        VBox container = new VBox();
        container.getStyleClass().add("menu-box");
        Map<TypeOfButton, ButtonBase> buttons = new HashMap<>(Map.of(
                TypeOfButton.homeMenu, homeButton,
                TypeOfButton.inputMenu, inputButton,
                TypeOfButton.complete, completeButton,
                TypeOfButton.filter, filterButton,
                TypeOfButton.overdue, overdueButton
        ));
        return new ButtonBundle(buttons, container);
    }

    private InputBundle inputPanel() {
        TextField title = builderUtility.makeShortInput("title");
        TextField due = builderUtility.makeShortInput("due");
        TextArea description =  builderUtility.makeLongInput(100, "description");
        TextField tagInput = builderUtility.makeShortInput("tagInput");
        Map<InputFieldType, TextInputControl> inputs = new HashMap<>(Map.of(
                InputFieldType.title, title,
                InputFieldType.dueDate, due,
                InputFieldType.description, description,
                InputFieldType.tagInput, tagInput
        ));
        return new InputBundle(inputs, new HBox());
    }

    private VBox toastContainer() {
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
