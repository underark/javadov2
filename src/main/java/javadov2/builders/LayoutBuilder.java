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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        VBox taskVBox = builderUtility.makeTaskVBox();
        ScrollPane scrollPane = builderUtility.makeScrollPane(taskVBox);
        BorderPane borderPane = new BorderPane();
        ButtonBundle buttonBundle = leftMenuPanel();
        borderPane.setLeft(buttonBundle.getContainer());
        borderPane.setCenter(scrollPane);
        return new LayoutBundle(borderPane, buttonBundle.getButtons(), Map.of(), taskVBox, Map.of());
    }

    private LayoutBundle input() {
        VBox taskVBox = builderUtility.makeTaskVBox();
        ScrollPane scrollPane = builderUtility.makeScrollPane(taskVBox);
        InputBundle inputBundle = inputPanel();
        taskVBox.getChildren().add(builderUtility.makeStylizedLabel("Title", "strong"));
        taskVBox.getChildren().add(inputBundle.findInputByType(InputFieldType.title));
        taskVBox.getChildren().add(builderUtility.makeStylizedLabel("Due date", "strong"));
        taskVBox.getChildren().add(inputBundle.findInputByType(InputFieldType.dueDate));
        taskVBox.getChildren().add(builderUtility.makeStylizedLabel("Description", "strong"));
        taskVBox.getChildren().add(inputBundle.findInputByType(InputFieldType.description));
        taskVBox.getChildren().add(builderUtility.makeStylizedLabel("Tag", "strong"));
        taskVBox.getChildren().add(inputBundle.findInputByType(InputFieldType.tagInput));
        Button saveButton = builderUtility.makeStylizedButton("Save", "form-btn", "save");
        HBox bottom = new HBox(saveButton);

        ButtonBundle buttonBundle = leftMenuPanel();
        buttonBundle.addButton(TypeOfButton.save, saveButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(buttonBundle.getContainer());
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(bottom);
        return new LayoutBundle(borderPane, buttonBundle.getButtons(), inputBundle.getInputs(), taskVBox, Map.of());
    }

    private LayoutBundle edit() {
        VBox taskVBox = builderUtility.makeTaskVBox();
        ScrollPane scrollPane = builderUtility.makeScrollPane(taskVBox);
        InputBundle inputBundle = inputPanel();
        taskVBox.getChildren().add(builderUtility.makeStylizedLabel("Title", "strong"));
        taskVBox.getChildren().add(inputBundle.findInputByType(InputFieldType.title));
        taskVBox.getChildren().add(builderUtility.makeStylizedLabel("Due date", "strong"));
        taskVBox.getChildren().add(inputBundle.findInputByType(InputFieldType.dueDate));
        taskVBox.getChildren().add(builderUtility.makeStylizedLabel("Description", "strong"));
        taskVBox.getChildren().add(inputBundle.findInputByType(InputFieldType.description));
        taskVBox.getChildren().add(builderUtility.makeStylizedLabel("Tag", "strong"));
        taskVBox.getChildren().add(inputBundle.findInputByType(InputFieldType.tagInput));
        Button editButton = builderUtility.makeStylizedButton("Save", "form-btn", "save");
        HBox bottom = new HBox(editButton);

        ButtonBundle buttonBundle = leftMenuPanel();
        buttonBundle.addButton(TypeOfButton.edit, editButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(buttonBundle.getContainer());
        borderPane.setCenter(taskVBox);
        borderPane.setBottom(bottom);
        return new LayoutBundle(borderPane, buttonBundle.getButtons(), inputBundle.getInputs(), taskVBox, Map.of());
    }

    private LayoutBundle filter() {
        VBox taskVBox = builderUtility.makeTaskVBox();
        ScrollPane scrollPane = builderUtility.makeScrollPane(taskVBox);
        ButtonBundle buttonBundle = leftMenuPanel();
        Button searchButton = builderUtility.makeStylizedButton("search", "form-btn", "search");
        buttonBundle.addButton(TypeOfButton.search, searchButton);
        InputBundle inputBundle = new InputBundle(Map.of(InputFieldType.tagSearch, builderUtility.makeShortInput("tagSearch")), new HBox());
        ComboBox selector = new ComboBox(FXCollections.observableArrayList("title", "date", "tag"));
        taskVBox.getChildren().addAll(inputBundle.findInputByType(InputFieldType.tagSearch), searchButton, selector);
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(buttonBundle.getContainer());
        borderPane.setCenter(scrollPane);
        return new LayoutBundle(borderPane, buttonBundle.getButtons(), inputBundle.getInputs(), taskVBox, Map.of(ComboBoxType.filter, selector));
    }

    private LayoutBundle filterDisplay() {
        VBox taskVBox = builderUtility.makeTaskVBox();
        ScrollPane scrollPane = builderUtility.makeScrollPane(taskVBox);
        ButtonBundle buttonBundle = leftMenuPanel();
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(buttonBundle.getContainer());
        borderPane.setCenter(scrollPane);
        return new LayoutBundle(borderPane, buttonBundle.getButtons(), Map.of(), taskVBox, Map.of());
    }

    private LayoutBundle emptyFilterDisplay() {
        ButtonBundle menu = leftMenuPanel();
        HBox center = new HBox(builderUtility.makeStylizedLabel("No tasks found!", "strong"));
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(menu.getContainer());
        borderPane.setCenter(center);
        return new LayoutBundle(borderPane, menu.getButtons(), Map.of(), new VBox(), Map.of());
    }

    private LayoutBundle overdue() {
        VBox taskVBox = builderUtility.makeTaskVBox();
        ScrollPane scrollPane = builderUtility.makeScrollPane(taskVBox);
        BorderPane borderPane = new BorderPane();
        ButtonBundle bundle = leftMenuPanel();
        borderPane.setLeft(bundle.getContainer());
        borderPane.setCenter(scrollPane);
        return new LayoutBundle(borderPane, bundle.getButtons(), Map.of(), taskVBox, Map.of());
    }

    private LayoutBundle complete() {
        VBox taskVBox = builderUtility.makeTaskVBox();
        ScrollPane scrollPane = builderUtility.makeScrollPane(taskVBox);
        BorderPane borderPane = new BorderPane();
        ButtonBundle menuButtons = leftMenuPanel();
        borderPane.setLeft(menuButtons.getContainer());
        borderPane.setCenter(scrollPane);
        return new LayoutBundle(borderPane, menuButtons.getButtons(), Map.of(), taskVBox, Map.of());
    }

    private ButtonBundle leftMenuPanel() {
        Button homeButton = builderUtility.makeStylizedButton("Home", "menu-btn", "home");
        Button inputButton = builderUtility.makeStylizedButton("New task", "menu-btn", "input");
        Button completeButton = builderUtility.makeStylizedButton("Completed", "menu-btn", "complete");
        Button filterButton = builderUtility.makeStylizedButton("Search", "menu-btn", "searchTag");
        Button overdueButton = builderUtility.makeStylizedButton("Overdue", "menu-btn", "overdue");
        VBox container = new VBox();
        container.setSpacing(15);
        container.setPadding(new Insets(5));
        container.setFillWidth(true);
        container.setPrefWidth(130);

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
