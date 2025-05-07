package javadov2.app;

import javadov2.builders.LayoutBuilder;
import javadov2.controllers.InputInteractor;
import javadov2.controllers.LayoutManager;
import javadov2.controllers.ViewController;

import javadov2.enums.ComboBoxType;
import javadov2.enums.InputFieldType;
import javadov2.enums.TypeOfButton;
import javadov2.service.MethodService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        // These guys do the static UI stuff
        LayoutBuilder layoutBuilder = new LayoutBuilder();
        LayoutManager layoutManager = new LayoutManager(layoutBuilder);
        layoutManager.initialize();
        MethodService methodService = new MethodService(layoutManager.giveLayoutSwitcher(), layoutManager.findButtonsByType(TypeOfButton.save), layoutManager.findButtonsByType(TypeOfButton.search), layoutManager.findInputsByType(InputFieldType.tagInput), layoutManager.findInputsByType(InputFieldType.tagSearch), layoutManager.findInputsByType(InputFieldType.title), layoutManager.findInputsByType(InputFieldType.dueDate), layoutManager.findInputsByType(InputFieldType.description), new ViewController(layoutManager.getDisplays()), new InputInteractor());
        methodService.setUpStatic();

        stage.setScene(new Scene(layoutManager.giveRoot(), 800, 800));
        stage.show();
    }
}
