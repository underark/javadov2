package javadov2.objects;

public class Task {
    private String title;
    private String dueDate;
    private String description;
    private String tag;
    private boolean completed;

    public Task(String title, String dueDate, String description, String tag) {
        //this.number = number;
        this.title = title;
        this.dueDate = dueDate;
        this.description = description;
        this.tag = tag;
        this.completed = false;
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

    public String getTag() {
        return tag;
    }

    public boolean getCompletion() {
        return completed;
    }

    public boolean changeCompleted(boolean completed) {
        this.completed = completed;
        return getCompletion();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(String date) {
        this.dueDate = date;
    }

    public void setDescription(String text) {
        this.description = text;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
