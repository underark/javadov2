package javadov2.service;


import javadov2.controllers.TaskController;
import javadov2.objects.Task;
import javadov2.utilities.LocalDateUtility;

import java.util.ArrayList;
import java.util.Objects;

public class TaskService {
    private TaskController taskController = new TaskController();
    private LocalDateUtility localDateUtility;

    public TaskService(TaskController taskController) {
        this.localDateUtility = new LocalDateUtility();
    }

    public String saveTask(Task task) {
        String result = localDateUtility.checkDateInput(task);
        if (Objects.equals(result, "Task added to list")) {
            taskController.addTask(task);
        }
        return result;
    }

    private String deleteTask(Task task) {
        return taskController.deleteTask(task);
    }

    public String markCompleted(Task task) {
        return taskController.markCompleted(task);
    }

    private ArrayList<Task> getAllTasks() {
        return new ArrayList<>(taskController.getAllTasks());
    }

    public ArrayList<Task> findOverdueTasks() {
        ArrayList<Task> tasks = getAllTasks();
        return new ArrayList<>(tasks.stream()
                .filter(e -> localDateUtility.isOverdue(e))
                .toList());
    }

    private ArrayList<Task> getIncompleteTasks() {
        ArrayList<Task> tasks = getAllTasks();
        return new ArrayList<>(tasks.stream()
                .filter(e -> !e.getCompletion())
                .toList());
    }

    public ArrayList<Task> findCompleteTasks() {
        ArrayList<Task> tasks = getAllTasks();
        return new ArrayList<>(tasks.stream()
                .filter(Task::getCompletion)
                .toList());
    }

    public ArrayList<Task> searchTags(String tag) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        getAllTasks().stream()
                .forEach(task -> {
                    if (Objects.equals(task.getTag(), tag)) {
                        foundTasks.add(task);
                    }
                });
        return foundTasks;
    }
}
