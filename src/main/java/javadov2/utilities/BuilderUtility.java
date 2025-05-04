package javadov2.utilities;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BuilderUtility {
    public Label makeStylizedLabel(String text, String style) {
        Label label = new Label(text);
        label.getStyleClass().add(style);
        label.setPadding(new Insets(20));
        return label;
    }

    public Button makeStylizedButton(String text, String style, String id) {
        Button button = new Button(text);
        button.getStyleClass().add(style);
        button.setId(id);
        return button;
    }

    public TextField makeShortInput(String id) {
        TextField input = new TextField();
        input.getStyleClass().add("text-inputMenu-short");
        input.setId(id);
        return input;
    }

    public TextArea makeLongInput(int size, String id) {
        TextArea input = new TextArea();
        input.setPrefHeight(size);
        input.setWrapText(true);
        input.setId(id);
        return input;
    }
}
