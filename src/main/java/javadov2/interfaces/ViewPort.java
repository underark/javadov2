package javadov2.interfaces;

import javadov2.enums.UpdateType;
import javadov2.objects.Task;
import javafx.scene.layout.GridPane;

public interface ViewPort {
    void updateDisplay(GridPane display, UpdateType type, Task task);
}
