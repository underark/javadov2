package javadov2.interfaces;

import javadov2.enums.InputStringType;
import javadov2.objects.ResultInfo;
import javadov2.objects.Task;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;


public interface Interactor {
    ResultInfo createTaskFromInput();
    Task editTask(Task task);
    void markTaskCompleted(Task task);
    StringProperty getProperty(InputStringType type);
    ArrayList<Task> searchTag();
}
