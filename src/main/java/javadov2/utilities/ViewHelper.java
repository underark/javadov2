package javadov2.utilities;

import javadov2.enums.LayoutType;
import javadov2.interfaces.ViewPort;
import javadov2.objects.Task;
import javadov2.objects.TaskNode;

public class ViewHelper {
    ViewPort viewController;

    public ViewHelper(ViewPort viewController) {
        this.viewController = viewController;
    }
    public TaskNode taskToNode(Task task) {
        // This currently doesn't work totally as I'd like. It only puts it in completed once marked completed
        // But it would be nice if you could move it back again
        // Consider making a new class that implements the interface to handle this logic
        return new TaskNode(task, t -> {
            task.changeCompleted(!task.getCompletion());
            if (task.getCompletion()) {
                viewController.addToDisplay(LayoutType.complete, task);
                viewController.removeFromDisplay(LayoutType.todo, task);
            } else {
                viewController.addToDisplay(LayoutType.todo, task);
                viewController.removeFromDisplay(LayoutType.complete, task);
            }
        });
    }
}
