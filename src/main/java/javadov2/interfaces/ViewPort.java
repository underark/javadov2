package javadov2.interfaces;

import javadov2.enums.LayoutType;
import javadov2.objects.Task;
import javadov2.objects.TaskNode;

import java.util.ArrayList;


public interface ViewPort {
    void addToDisplay(LayoutType type, Task task);
    void addToDisplay(LayoutType type, ArrayList<Task> tasks);
    void removeFromDisplay(LayoutType type, Task task);
    void removeFromDisplay(Task task);
    TaskNode getShownTask(Task task);
    void displayToast(String string);
}
