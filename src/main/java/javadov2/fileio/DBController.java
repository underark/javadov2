package javadov2.fileio;

import javadov2.objects.ResultInfo;
import javadov2.objects.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBController {
    private final String url;

    public DBController(String url) {
        this.url = url;
        createDB();
    }

    private void createDB() {
        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement()
        ) {
            statement.execute("CREATE TABLE IF NOT EXISTS tasks (id integer PRIMARY KEY, title string NOT NULL, date string NOT NULL, description string, tag string, completion boolean NOT NULL)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultInfo addEntry(Task task) {
        String sql = "INSERT INTO tasks(id, title, date, description, tag, completion) VALUES (?,?,?,?,?,?)";
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setInt(1, task.getId());
            statement.setString(2, task.getTitle());
            statement.setString(3, task.getDueDate());
            statement.setString(4, task.getDescription());
            statement.setString(5, task.getTag());
            statement.setBoolean(6, task.getCompletion());
            statement.executeUpdate();
            return new ResultInfo("Task added to list", task);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ResultInfo("Error occurred", null);
    }

    public ResultInfo changeCompletion(Task task) {
        String sql = "UPDATE tasks SET completion = ? WHERE id = ?";
        try (
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            int completion = (task.getCompletion()) ? 1 : 0;
            statement.setInt(1, completion);
            statement.setInt(2, task.getId());
            statement.executeUpdate();
            return new ResultInfo("Task completion changed", task);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new ResultInfo("Task completion could not be changed", null);
    }

    public void editTask(Task task) {
        String sql = "UPDATE tasks SET title = ?, date = ?, description = ?, tag = ?, completion = ? WHERE id = ?";
        try (
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDueDate());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getTag());
            statement.setBoolean(5, task.getCompletion());
            statement.setInt(6, task.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Task> getIncompleteTasks() {
        String sql = "SELECT * FROM tasks WHERE completion = 0";
        ArrayList<Task> tasks = new ArrayList<>();
        try (
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String dueDate = resultSet.getString("date");
                Optional<String> description = Optional.ofNullable(resultSet.getString("description"));
                Optional<String> tag = Optional.ofNullable(resultSet.getString("tag"));
                boolean completion = (resultSet.getInt("completion") == 0) ? false : true;
                Task task = new Task(id, title, dueDate, description.orElse(""), tag.orElse(""), completion);
                System.out.println(task.getCompletion());
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public int getNextTaskNumber() {
        String sql = "SELECT MAX(id) FROM tasks";
        try (
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()
        ) {
            return resultSet.getInt(1) + 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }
}
