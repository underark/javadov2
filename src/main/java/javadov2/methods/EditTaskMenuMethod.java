package javadov2.methods;

import javadov2.enums.InputStringType;
import javadov2.enums.LayoutType;
import javadov2.interfaces.Interactor;
import javadov2.interfaces.NodeMethod;
import javadov2.objects.Task;
import javadov2.utilities.LayoutSwitcher;

public class EditTaskMenuMethod implements NodeMethod {
    private LayoutSwitcher layoutSwitcher;
    private Interactor inputInteractor;

    public EditTaskMenuMethod(LayoutSwitcher layoutSwitcher, Interactor inputInteractor) {
        this.layoutSwitcher = layoutSwitcher;
        this.inputInteractor = inputInteractor;
    }

    @Override
    public void onPress(Task task) {
        layoutSwitcher.switchLayout(LayoutType.edit);
        inputInteractor.getProperty(InputStringType.title).setValue(task.getTitle());
        inputInteractor.getProperty(InputStringType.date).setValue(task.getDueDate());
        inputInteractor.getProperty(InputStringType.description).setValue(task.getDescription());
        inputInteractor.getProperty(InputStringType.tag).setValue(task.getTag());
    }
}
