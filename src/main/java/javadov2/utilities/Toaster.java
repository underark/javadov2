package javadov2.utilities;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class Toaster {
    private final VBox toastContainer;

    public Toaster(VBox toastContainer) {
        this.toastContainer = toastContainer;
    }

    public HBox makeToastMessage(String string) {
        Label message = new Label(string);
        HBox box = new HBox(message);
        FadeTransition ft = new FadeTransition(Duration.seconds(1), box);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        SequentialTransition st = new SequentialTransition(new PauseTransition(Duration.seconds(3)), ft);
        st.setOnFinished(event -> toastContainer.getChildren().remove(message));
        st.play();
        return box;
    }
}
