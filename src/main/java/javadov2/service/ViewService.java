package javadov2.service;

import javadov2.controllers.TaskController;
import javadov2.controllers.ViewController;

public class ViewService {
    private ViewController viewController;
    private TaskService taskService;

    public ViewService(ViewController viewController) {
        this.viewController = viewController;
        taskService = new TaskService(new TaskController());
    }
}
