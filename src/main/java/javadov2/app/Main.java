package javadov2.app;

import javadov2.builders.LayoutBuilder;
import javadov2.controllers.LayoutManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        LayoutBuilder layoutBuilder = new LayoutBuilder();
        LayoutManager layoutManager = new LayoutManager(layoutBuilder);
        layoutManager.initialize();
        stage.setScene(new Scene(layoutManager.giveRoot(), 800, 800));
        stage.show();
    }
}
