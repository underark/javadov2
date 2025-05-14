package javadov2.interfaces;

import javadov2.enums.InputStringType;
import javadov2.objects.TaskInfo;
import javafx.beans.property.StringProperty;




public interface Interactor {
    TaskInfo getUserInput();
    StringProperty getProperty(InputStringType type);
    String getStringValue(InputStringType string);
}
