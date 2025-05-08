package javadov2.controllers;


import javadov2.enums.LayoutType;
import javadov2.interfaces.ViewPort;
import javadov2.objects.Task;
import javadov2.objects.TaskNode;
import javadov2.utilities.TaskNodeFactory;
import javafx.scene.layout.GridPane;

import java.util.*;

public class ViewController implements ViewPort {
    private Map<LayoutType, GridPane> displays;
    private Map<LayoutType, Map<Task, TaskNode>> shownTasks;
    private TaskNodeFactory taskNodeFactory;

    public ViewController(Map<LayoutType, GridPane> displays) {
        this.displays = displays;
        taskNodeFactory = new TaskNodeFactory();
        shownTasks = new HashMap<>();
        for (LayoutType type : LayoutType.values()) {
            shownTasks.put(type, new HashMap<>());
        }
    }

    public void addToDisplay(LayoutType type, Task task) {
        if (displays.containsKey(type)) {
            GridPane display = displays.get(type);
            TaskNode taskNode;
            if (Objects.isNull(getShownTask(task))) {
                taskNode = new TaskNode(task);
                shownTasks.get(type).put(task, taskNode);
            } else {
                taskNode = getShownTask(task);
                shownTasks.get(type).put(task, taskNode);
            }
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

    public void removeFromDisplay(Task task) {
        displays.forEach((type, gridPane) -> {
            gridPane.getChildren().remove(getShownTask(task).getNode());
        });
    }

    public void addToDisplay(LayoutType type, ArrayList<Task> tasks) {
        displays.get(type).getChildren().clear();
        if (displays.containsKey(type)) {
            GridPane display = displays.get(type);
            List<TaskNode> taskNodes = tasks.stream()
                    .map(task -> taskNodeFactory.makeTaskNode(task))
                    .toList();
            taskNodes.forEach(node -> display.addRow(display.getRowCount() + 1, node.getNode()));
        }
    }

    public TaskNode getShownTask(Task task) {
        for (LayoutType type : LayoutType.values()) {
            if (shownTasks.get(type).containsKey(task)) {
                return shownTasks.get(type).get(task);
            }
        }
        return null;
    }
}
