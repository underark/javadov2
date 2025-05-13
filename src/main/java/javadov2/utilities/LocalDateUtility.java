package javadov2.utilities;

import javadov2.objects.Task;
import javadov2.objects.TaskInfo;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalDateUtility {
    public String checkDateInput(TaskInfo taskInfo) {
        if (!checkValidDateFormat(taskInfo.dueDate())) {
            return "Format error. Use YYY--MM--DD";
        } else if (!checkValidDate(taskInfo.dueDate())) {
            return "Cannot make task with past date";
        }
        return "Task added to list";
    }

    public boolean isOverdue(Task task) {
        LocalDate date = LocalDate.parse(task.getDueDate());
        return date.isBefore(LocalDate.now());
    }

    private boolean checkValidDateFormat(String date) {
        Pattern pattern = Pattern.compile("\\d{4}+-\\d{2}+-\\d{2}+");
        Matcher matcher = pattern.matcher(date);
        return matcher.find();
    }

    private boolean checkValidDate(String date) {
        LocalDate check = LocalDate.parse(date);
        return check.isBefore(LocalDate.now());
    }
}
