package javadov2.utilities;

import javadov2.objects.Task;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalDateUtility {
    public String checkDateInput(Task task) {
        if (!checkValidDateFormat(task)) {
            return "Format error. Use YYY--MM--DD";
        } else if (!checkValidDate(task)) {
            return "Cannot make task with past date";
        }
        return "Task added to list";
    }

    public boolean isOverdue(Task task) {
        LocalDate date = LocalDate.parse(task.getDueDate());
        return date.isBefore(LocalDate.now());
    }

    private boolean checkValidDateFormat(Task task) {
        Pattern pattern = Pattern.compile("\\d{4}+-\\d{2}+-\\d{2}+");
        Matcher matcher = pattern.matcher(task.getDueDate());
        return matcher.find();
    }

    private boolean checkValidDate(Task task) {
        LocalDate date = LocalDate.parse(task.getDueDate());
        return !date.isBefore(LocalDate.now());
    }
}
