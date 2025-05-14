package javadov2.interfaces;

import javadov2.enums.ComboBoxType;
import javadov2.enums.InputStringType;
import javadov2.enums.LayoutType;
import javadov2.objects.TaskInfo;
import javafx.beans.property.StringProperty;




public interface Interactor {
    TaskInfo getUserInput();
    StringProperty getProperty(InputStringType type);
    String getStringValue(InputStringType string);
    Object getComboBoxValue(LayoutType layoutType, ComboBoxType boxType);
}
