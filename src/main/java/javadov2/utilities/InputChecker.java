package javadov2.utilities;

import javadov2.objects.ResultInfo;
import javadov2.objects.Task;
import javafx.beans.property.StringProperty;


public class InputChecker {
    public ResultInfo checkTitle(StringProperty title, Task task) {
        if (title.getValue() == null) {
            return new ResultInfo("Title cannot be blank", null);
        }
        return new ResultInfo("", task);
    }

    public ResultInfo checkDate(StringProperty date, Task task) {
        if (date == null) {
            return new ResultInfo("Date cannot be blank", null);
        }
        return new ResultInfo("", task);
    }
}
