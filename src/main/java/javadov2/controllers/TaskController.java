package javadov2.controllers;

import javadov2.objects.Task;
import javadov2.objects.TaskInfo;

import java.util.ArrayList;

public class TaskController {
    private ArrayList<Task> taskList;

    public TaskController() {
        taskList = new ArrayList<>();
    }

    public ArrayList<Task> getAllTasks() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public String deleteTask(Task task) {
        boolean result = taskList.remove(task);
        if (result) {
            return "Task deleted";
        } else {
            return "Task not found";
        }
    }

    public String markCompleted(Task task) {
        if (task.changeCompleted(!task.getCompletion())) {
            return "Task marked as completed";
        } else {
            return "Task marked as incomplete";
        }
    }

    public Task editTask(Task task, TaskInfo taskInfo) {
        Task editTask = taskList.get(taskList.indexOf(task));
        editTask.setTitle(taskInfo.title());
        editTask.setDueDate(taskInfo.dueDate());
        editTask.setDescription(taskInfo.description());
        editTask.setTag(taskInfo.tag());
        editTask.changeCompleted(false);
        return editTask;
    }
}
