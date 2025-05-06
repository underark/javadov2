package javadov2.interfaces;

import javadov2.enums.LayoutType;
import javadov2.objects.Task;


public interface ViewPort {
    void addToDisplay(LayoutType type, Task task);
    void removeFromDisplay(LayoutType type, Task task);
}
