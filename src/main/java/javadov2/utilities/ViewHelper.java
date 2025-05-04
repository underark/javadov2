package javadov2.utilities;

import javadov2.objects.Task;
import javadov2.objects.TaskNode;

public class ViewHelper {
    public TaskNode taskToNode(Task task) {
        return new TaskNode(task.getTitle(), task.getDueDate(), task.getDescription());
    }
}
