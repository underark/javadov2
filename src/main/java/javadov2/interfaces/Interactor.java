package javadov2.interfaces;

import javadov2.enums.InputStringType;
import javadov2.objects.Task;
import javafx.beans.property.StringProperty;

public interface Interactor {
    Task createTaskFromInput();
    StringProperty getProperty(InputStringType type);

}
