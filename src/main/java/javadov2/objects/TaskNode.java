package javadov2.objects;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class TaskNode {
    Node title;
    Node dueDate;
    Node description;

    public TaskNode(String title, String dueDate, String description) {
        this.title = new Label(title);
        this.dueDate = new Label(dueDate);
        this.description = new Label(description);
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
}
