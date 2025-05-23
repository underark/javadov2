package javadov2.interfaces;

import javadov2.enums.LayoutType;
import javadov2.objects.LayoutBundle;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Map;

public interface Builder {
    Map<LayoutType, LayoutBundle> build();
    StackPane giveRoot();
    VBox giveToastContainer();
}
