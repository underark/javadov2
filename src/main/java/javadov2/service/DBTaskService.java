package javadov2.service;

import javadov2.fileio.DBController;
import javadov2.interfaces.ObjectService;
import javadov2.objects.ResultInfo;
import javadov2.objects.Task;
import javadov2.objects.TaskInfo;
import javadov2.utilities.LocalDateUtility;

import java.util.List;
import java.util.stream.Collectors;

public class DBTaskService implements ObjectService {
    private DBController dbController;
    private LocalDateUtility localDateUtility;

    public DBTaskService(DBController dbController) {
        this.dbController = dbController;
        localDateUtility = new LocalDateUtility();
    }

    public ResultInfo saveTask(TaskInfo taskInfo) {
        if (taskInfo.title() == null) {
            return new ResultInfo("Title cannot be empty", null);
        }

        if (taskInfo.dueDate() == null) {
            return new ResultInfo("Due date cannot be empty", null);
        }

        if (taskInfo.tag() != null && taskInfo.tag().contains(" ")) {
            return new ResultInfo("Tag cannot contain spaces", null);
        }

        if (!localDateUtility.checkDateInput(taskInfo)) {
            return new ResultInfo("Ensure date is formatted YYYY-MM-DD and is in the future", null);
        }

        return dbController.addEntry(new Task(getNextTaskNumber(), taskInfo.title(), taskInfo.dueDate(), taskInfo.description(), taskInfo.tag()));
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

        if (!localDateUtility.checkDateInput(taskInfo)) {
            return new ResultInfo("Ensure date is formatted YYYY-MM-DD and is in the future", null);
        }

        return dbController.editTask(task, taskInfo);
    }

    public List<Task> searchTag(String term) {
        return dbController.searchTag(term);
    }

    public List<Task> searchTitle(String term) {
        return dbController.searchTitle(term);
    }

    public List<Task> searchDate(String term) {
        return dbController.searchDate(term);
    }

    public List<Task> getIncompleteTasks() {
        return dbController.getIncompleteTasks();
    }

    public List<Task> getOverdueTasks() {
        List<Task> tasks = dbController.getIncompleteTasks();
        return tasks.stream().
                filter(task -> localDateUtility.isOverdue(task))
                .collect(Collectors.toList());
    }

    public List<Task> getCompleteTasks() {
        return dbController.getCompleteTasks();
    }

    public int getNextTaskNumber() {
        return dbController.getNextTaskNumber();
    }
}
