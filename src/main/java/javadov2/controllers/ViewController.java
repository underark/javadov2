package javadov2.controllers;


import javadov2.enums.LayoutType;
import javadov2.enums.UpdateType;
import javadov2.interfaces.ViewPort;
import javadov2.objects.LayoutBundle;
import javadov2.objects.Task;
import javadov2.objects.TaskNode;
import javadov2.utilities.ViewHelper;
import javafx.scene.layout.GridPane;

import java.util.Map;
import java.util.Objects;

public class ViewController implements ViewPort {
    private Map<LayoutType, LayoutBundle> layouts;
    private ViewHelper viewHelper;

    public ViewController(Map<LayoutType, LayoutBundle> layouts) {
        this.layouts = layouts;
        viewHelper = new ViewHelper();
    }

    public void updateDisplay(GridPane display, UpdateType type, Task task) {
        if (Objects.equals(type, UpdateType.add)) {
            TaskNode taskNode = viewHelper.taskToNode(task);
            int insertRow = display.getRowCount() + 1;
            display.addRow(insertRow, taskNode.getTitle(), taskNode.getDueDate(), taskNode.getDescription());
        }
    }
}
