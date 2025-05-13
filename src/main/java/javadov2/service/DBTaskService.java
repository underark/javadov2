package javadov2.service;

import javadov2.fileio.DBController;
import javadov2.interfaces.ObjectService;
import javadov2.objects.ResultInfo;
import javadov2.objects.Task;
import javadov2.objects.TaskInfo;
import javadov2.utilities.LocalDateUtility;

import java.util.ArrayList;
import java.util.List;

public class DBTaskService implements ObjectService {
    private DBController dbController;
    private LocalDateUtility localDateUtility;

    public DBTaskService(DBController dbController) {
        this.dbController = dbController;
        localDateUtility = new LocalDateUtility();
    }

    public ResultInfo saveTask(TaskInfo taskInfo) {
        String result = localDateUtility.checkDateInput(taskInfo);

        if (taskInfo.title() == null) {
            return new ResultInfo("Title cannot be empty", null);
        }

        if (taskInfo.dueDate() == null) {
            return new ResultInfo("Due date cannot be empty", null);
        }

        if (taskInfo.tag().contains(" ")) {
            return new ResultInfo("Tag cannot contain spaces", null);
        }

        if (result.equalsIgnoreCase("Task added to list")) {
            return dbController.addEntry(new Task(getNextTaskNumber(), taskInfo.title(), taskInfo.dueDate(), taskInfo.description(), taskInfo.tag()));
        }
        return new ResultInfo(result, null);
    }

    public ResultInfo changeCompletion(Task task) {
        return dbController.changeCompletion(task);
    }

    public ResultInfo editTask(Task task, TaskInfo taskInfo) {
        if (taskInfo.title() == null) {
            return new ResultInfo("Title cannot be empty", null);
        }

        if (taskInfo.dueDate() == null) {
            return new ResultInfo("Due date cannot be empty", null);
        }

        String result = localDateUtility.checkDateInput(taskInfo);
        if (result.equalsIgnoreCase("Task added to list")) {
            return dbController.addEntry(new Task(getNextTaskNumber(), taskInfo.title(), taskInfo.dueDate(), taskInfo.description(), taskInfo.tag()));
        }

        return dbController.editTask(task, taskInfo);
    }

    //TODO
    public ArrayList<Task> searchTags(String tag) {
        return null;
    }

    public List<Task> getIncompleteTasks() {
        return dbController.getIncompleteTasks();
    }

    public List<Task> getCompleteTasks() {
        return dbController.getCompleteTasks();
    }

    public int getNextTaskNumber() {
        return dbController.getNextTaskNumber();
    }
}
