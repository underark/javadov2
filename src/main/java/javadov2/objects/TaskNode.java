package javadov2.objects;

import javadov2.enums.TypeOfButton;
import javadov2.interfaces.NodeMethod;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;

import java.util.EnumMap;
import java.util.Map;

public class TaskNode {
    Task task;
    Node title;
    Node dueDate;
    Node description;
    Map<TypeOfButton, ButtonBase> buttons;

    public TaskNode(Task task, NodeMethod onComplete) {
        this.task = task;
        this.title = new Label(task.getTitle());
        this.dueDate = new Label(task.getDueDate());
        this.description = new Label(task.getDescription());
        buttons = Map.of(
            TypeOfButton.complete, new Button("mark complete")
        );
        buttons.get(TypeOfButton.complete).setOnAction(e -> onComplete.onPress(task));
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
}
