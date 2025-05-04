package javadov2.interfaces;

import javadov2.enums.InputStringType;
import javadov2.objects.Task;
import javafx.beans.property.StringProperty;

public interface Interactor {
    Task createTaskFromInput();
    void markTaskCompleted(Task task);
    StringProperty getProperty(InputStringType type);

}
