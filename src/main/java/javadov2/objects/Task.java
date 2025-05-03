package javadov2.objects;

public class Task {
    private int number;
    private String title;
    private String dueDate;
    private String description;
    private boolean completed;

    public Task(int number, String title, String dueDate, String description) {
        this.number = number;
        this.title = title;
        this.dueDate = dueDate;
        this.description = description;
        this.completed = false;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getDescription() {
        return description;
    }

    public boolean getCompletion() {
        return completed;
    }

    public boolean changeCompleted(boolean completed) {
        this.completed = completed;
        return getCompletion();
    }
}
