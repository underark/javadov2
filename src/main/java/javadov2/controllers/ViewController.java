package javadov2.controllers;


import javadov2.enums.LayoutType;
import javadov2.interfaces.ViewPort;
import javadov2.objects.Task;
import javadov2.objects.TaskNode;
import javadov2.utilities.ViewHelper;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ViewController implements ViewPort {
    private Map<LayoutType, GridPane> displays;
    private Map<LayoutType, Map<Task, TaskNode>> shownTasks;
    private ViewHelper viewHelper;

    public ViewController(Map<LayoutType, GridPane> displays) {
        this.displays = displays;
        shownTasks = new HashMap<>();
        for (LayoutType type : LayoutType.values()) {
            shownTasks.put(type, new HashMap<>());
        }
        viewHelper = new ViewHelper(this);
    }

    public void addToDisplay(LayoutType type, Task task) {
        if (displays.containsKey(type)) {
            GridPane display = displays.get(type);
            TaskNode taskNode = viewHelper.taskToNode(task);
            shownTasks.get(type).put(task, taskNode);
            int insertRow = display.getRowCount() + 1;
            display.addRow(insertRow, taskNode.getNode());
        }
    }

    public void removeFromDisplay(LayoutType type, Task task) {
        if (displays.containsKey(type)) {
            GridPane display = displays.get(type);
            TaskNode node = shownTasks.get(type).get(task);
            display.getChildren().remove(node.getNode());
            shownTasks.get(type).remove(task);
        }
    }

    public void addToDisplay(LayoutType type, ArrayList<Task> tasks) {
        if (displays.containsKey(type)) {
            GridPane display = displays.get(type);
            List<TaskNode> taskNodes = tasks.stream()
                    .map(task -> viewHelper.taskToNode(task))
                    .toList();
            taskNodes.forEach(node -> display.addRow(display.getRowCount() + 1, node.getNode()));
        }
    }

}
