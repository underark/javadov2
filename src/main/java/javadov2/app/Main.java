package javadov2.app;

import javadov2.builders.LayoutBuilder;
import javadov2.controllers.InputInteractor;
import javadov2.controllers.LayoutManager;
import javadov2.controllers.ViewController;
import javadov2.interfaces.Interactor;
import javadov2.interfaces.ViewPort;
import javadov2.service.MethodService;
import javadov2.utilities.LayoutSwitcher;
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
        LayoutSwitcher layoutSwitcher = new LayoutSwitcher(layoutManager.giveRoot(), layoutManager.giveToastContainer(), layoutManager.giveLayouts());
        Interactor inputInteractor = new InputInteractor();
        ViewPort viewController = new ViewController(layoutManager.giveDisplays(), layoutManager.giveToastContainer());
        MethodService methodService = new MethodService(layoutManager.giveButtons(), layoutManager.giveInputs(), layoutSwitcher, viewController, inputInteractor);
        methodService.wireStaticButtonsAndInput();

        stage.setScene(new Scene(layoutManager.giveRoot(), 800, 800));
        stage.show();
    }
}
