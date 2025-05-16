package javadov2.objects;

import javadov2.enums.TypeOfButton;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Map;

public class TaskNode {
    Task task;
    Node title;
    Node dueDate;
    Node description;
    Node tag;
    HBox node;
    Map<TypeOfButton, ButtonBase> buttons;

    public TaskNode(Task task) {
        this.task = task;
        title = new Label(task.getTitle());
        dueDate = new Label(task.getDueDate());
        description = new Label(task.getDescription());
        tag = new Label(task.getTag());
        buttons = Map.of(
            TypeOfButton.complete, new Button("mark complete"),
            TypeOfButton.editMenu, new Button("edit")
        );
        buttons.forEach(((typeOfButton, button) -> {button.setVisible(false);}));
        node = new HBox(title, dueDate, description, tag, getButton(TypeOfButton.complete), getButton(TypeOfButton.editMenu));
        node.setMaxWidth(Double.MAX_VALUE);
        node.setAlignment(Pos.CENTER);
        node.setSpacing(60);
        node.getStyleClass().add("task-node");
        node.setOnMouseEntered(event -> {
            buttons.forEach((type, button) -> button.setVisible(true));
        });
        node.setOnMouseExited(event -> {
            buttons.forEach((type, button) -> button.setVisible(false));
        });
    }

    public Node getTitle() {
        return title;
    }

    public Node getDueDate() {
        return dueDate;
    }

    public Node getDescription() {
        return description;
    }

    public ButtonBase addButton(TypeOfButton type, ButtonBase button) {
        if (!buttons.containsKey(type)) {
            buttons.put(type, button);
        }
        return button;
    }

    public ButtonBase getButton(TypeOfButton type) {
        return buttons.getOrDefault(type, null);
    }

    public Task getTask() {
        return task;
    }

    public Map<TypeOfButton, ButtonBase> getButtons() {
        return buttons;
    }

    public Node getNode() {
        return node;
    }

    public Node getNodeNoButtons() {
        node = new HBox(title, dueDate, description, tag);
        node.setMaxWidth(Double.MAX_VALUE);
        node.setAlignment(Pos.CENTER);
        node.setSpacing(60);
        node.getStyleClass().add("task-node");
        return node;
    }

    public void makeButtonsVisible() {
        buttons.forEach((type, button) -> button.setVisible(true));
    }
}
