package javadov2.interfaces;

import javadov2.enums.LayoutType;
import javadov2.objects.Task;
import javadov2.objects.TaskNode;

import java.util.List;
import java.util.Map;


public interface ViewPort {
    TaskNode addToDisplay(LayoutType type, Task task);
    void addToDisplay(LayoutType type, List<Task> tasks);
    void addToDisplayNoButtons(LayoutType type, List<Task> tasks);
    void removeFromDisplay(LayoutType type, Task task);
    void removeFromDisplay(Task task);
    TaskNode getShownTask(Task task);
    Map<Task, TaskNode> getExistingTasks();
    void displayToast(String string);
}
