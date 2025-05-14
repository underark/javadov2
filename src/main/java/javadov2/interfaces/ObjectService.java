package javadov2.interfaces;

import javadov2.objects.ResultInfo;
import javadov2.objects.Task;
import javadov2.objects.TaskInfo;

import java.util.ArrayList;
import java.util.List;

public interface ObjectService {
    ResultInfo saveTask(TaskInfo taskInfo);
    ResultInfo editTask(Task task, TaskInfo taskInfo);
    ResultInfo changeCompletion(Task task);
    List<Task> searchTags(String tag);
    List<Task> getIncompleteTasks();
    int getNextTaskNumber();
}
