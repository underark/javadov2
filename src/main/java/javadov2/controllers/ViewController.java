package javadov2.controllers;


import javadov2.enums.LayoutType;
import javadov2.interfaces.ViewPort;
import javadov2.objects.Task;
import javadov2.objects.TaskNode;
import javadov2.service.DBTaskService;
import javadov2.utilities.TaskNodeFactory;
import javadov2.utilities.Toaster;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.*;

public class ViewController implements ViewPort {
    private final VBox toastContainer;
    private final Map<LayoutType, VBox> displays;
    private Map<LayoutType, Map<Task, TaskNode>> shownTasks;
    private TaskNodeFactory taskNodeFactory;
    private Toaster toaster;

    public ViewController(DBTaskService dbTaskService, Map<LayoutType, VBox> displays, VBox toastContainer) {
        this.displays = displays;
        this.toastContainer = toastContainer;
        taskNodeFactory = new TaskNodeFactory();
        toaster = new Toaster(toastContainer);
        shownTasks = new HashMap<>();
        for (LayoutType type : LayoutType.values()) {
            shownTasks.put(type, new HashMap<>());
        }
        addToDisplay(LayoutType.todo, dbTaskService.getIncompleteTasks());
        addToDisplay(LayoutType.complete, dbTaskService.getCompleteTasks());
        addToDisplay(LayoutType.overdue, dbTaskService.getOverdueTasks());
    }

    public TaskNode addToDisplay(LayoutType type, Task task) {
        TaskNode taskNode;
        if (displays.containsKey(type)) {
            VBox display = displays.get(type);
            if (Objects.isNull(getShownTask(task))) {
                taskNode = new TaskNode(task);
                shownTasks.get(type).put(task, taskNode);
            } else {
                taskNode = getShownTask(task);
                shownTasks.get(type).put(task, taskNode);
            }
            display.getChildren().add(taskNode.getNode());
            return taskNode;
        }
        return null;
    }

    public void removeFromDisplay(LayoutType type, Task task) {
        if (displays.containsKey(type)) {
            VBox display = displays.get(type);
            TaskNode node = shownTasks.get(type).get(task);
            display.getChildren().remove(node.getNode());
            shownTasks.get(type).remove(task);
        }
    }

    public void removeFromDisplay(Task task) {
        displays.forEach((type, VBox) -> {
            VBox.getChildren().remove(getShownTask(task).getNode());
        });
    }

    public void addToDisplay(LayoutType type, List<Task> tasks) {
        Map<Task, TaskNode> nodeMap = new HashMap<>();
        displays.get(type).getChildren().clear();
        if (displays.containsKey(type)) {
            VBox display = displays.get(type);
            List<TaskNode> taskNodes = tasks.stream()
                    .map(task -> taskNodeFactory.makeTaskNode(task))
                    .toList();
            taskNodes.forEach(node -> {
                display.getChildren().add(node.getNode());
                nodeMap.put(node.getTask(), node);
            });
            shownTasks.put(type, nodeMap);
        }
    }

    public void addToDisplayNoButtons(LayoutType type, List<Task> tasks) {
        Map<Task, TaskNode> nodeMap = new HashMap<>();
        displays.get(type).getChildren().clear();
        if (displays.containsKey(type)) {
            VBox display = displays.get(type);
            List<TaskNode> taskNodes = tasks.stream()
                    .map(task -> taskNodeFactory.makeTaskNode(task))
                    .toList();
            taskNodes.forEach(node -> {
                display.getChildren().add(node.getNodeNoButtons());
                nodeMap.put(node.getTask(), node);
            });
            shownTasks.put(type, nodeMap);
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

    public Map<Task, TaskNode> getExistingTasks() {
        Map<Task, TaskNode> map = new HashMap<>();
        shownTasks.forEach((layout, tasks) -> map.putAll(tasks));
        return map;
    }

    public void displayToast(String string) {
        HBox toast = toaster.makeToastMessage(string);
        toastContainer.getChildren().add(toast);
    }
}
