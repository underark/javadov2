package javadov2.interfaces;

import javadov2.enums.UpdateType;
import javadov2.objects.Task;
import javadov2.objects.TaskNode;


public interface ViewPort {
    TaskNode updateDisplay(UpdateType type, Task task);
}
