package javadov2.controllers;


import javadov2.enums.LayoutType;
import javadov2.enums.TypeOfButton;
import javadov2.enums.UpdateType;
import javadov2.interfaces.ViewPort;
import javadov2.objects.Task;
import javadov2.objects.TaskNode;
import javadov2.utilities.ViewHelper;
import javafx.scene.layout.GridPane;

import java.util.Map;
import java.util.Objects;

public class ViewController implements ViewPort {
    private Map<LayoutType, GridPane> displays;
    private ViewHelper viewHelper;

    public ViewController(Map<LayoutType, GridPane> displays) {
        this.displays = displays;
        viewHelper = new ViewHelper();
    }

    public TaskNode updateDisplay(UpdateType type, Task task) {
        if (Objects.equals(type, UpdateType.add)) {
            GridPane toDoDisplay = displays.get(LayoutType.todo);
            TaskNode taskNode = viewHelper.taskToNode(task);
            int insertRow = toDoDisplay.getRowCount() + 1;
            toDoDisplay.addRow(insertRow, taskNode.getTitle(), taskNode.getDueDate(), taskNode.getDescription(), taskNode.getButton(TypeOfButton.complete));
            return taskNode;
        }
        return null;
    }
}
