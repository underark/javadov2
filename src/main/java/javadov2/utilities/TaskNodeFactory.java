package javadov2.utilities;

import javadov2.objects.Task;
import javadov2.objects.TaskNode;

public class TaskNodeFactory {
    public TaskNode makeTaskNode(Task task) {
        return new TaskNode(task);
    }
}
