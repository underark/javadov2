package javadov2.service;


import javadov2.controllers.TaskController;
import javadov2.fileio.DBController;
import javadov2.interfaces.ObjectService;
import javadov2.objects.ResultInfo;
import javadov2.objects.Task;
import javadov2.objects.TaskInfo;
import javadov2.utilities.LocalDateUtility;

import java.util.ArrayList;
import java.util.Objects;

public class InMemoryTaskService {
    private TaskController taskController = new TaskController();
    private LocalDateUtility localDateUtility;
    private DBController dbController;

    public InMemoryTaskService(TaskController taskController) {
        this.localDateUtility = new LocalDateUtility();
        dbController = new DBController("jdbc:sqlite:/home/alex/projects/java/JavaDoV2/sql/my.db");
    }

//    public ResultInfo saveTask(Task task) {
//        String result = localDateUtility.checkDateInput(task);
//        if (result.equalsIgnoreCase("Task added to list")) {
//            taskController.addTask(task);
//            dbController.addEntry(task);
//            return new ResultInfo(result, task);
//        }
//        return new ResultInfo(result, null);
//    }

    // Need to validate whether title input is blank or not too
//    public ResultInfo editTask(Task task, TaskInfo taskInfo) {
//        String result = localDateUtility.checkDateInput(new Task(1, taskInfo.title(), taskInfo.dueDate(), taskInfo.description(), taskInfo.tag()));
//        if (result.equalsIgnoreCase("Task added to list")) {
//            return new ResultInfo("Task edited", taskController.editTask(task, taskInfo));
//        }
//        return new ResultInfo(result, null);
//    }
    private ArrayList<Task> getAllTasks() {
        return new ArrayList<>(taskController.getAllTasks());
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
