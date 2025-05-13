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

        if (result.equalsIgnoreCase("Task added to list")) {
            return dbController.addEntry(new Task(getNextTaskNumber(), taskInfo.title(), taskInfo.dueDate(), taskInfo.description(), taskInfo.tag()));
        }
        return new ResultInfo(result, null);
    }

    public ResultInfo changeCompletion(Task task) {
        return dbController.changeCompletion(task);
    }

    //TODO
    public ResultInfo editTask(Task task, TaskInfo taskInfo) {
        return null;
    }
    //TODO
    public ArrayList<Task> searchTags(String tag) {
        return null;
    }

    public List<Task> getIncompleteTasks() {
        return dbController.getIncompleteTasks();
    }

    public int getNextTaskNumber() {
        return dbController.getNextTaskNumber();
    }
}
