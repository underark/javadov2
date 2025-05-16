package javadov2.utilities;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

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
        button.setWrapText(true);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setMaxWidth(Double.MAX_VALUE);
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

    public VBox makeTaskVBox() {
        VBox box = new VBox(10);
        box.setAlignment(Pos.TOP_CENTER);
        return box;
    }

    public ScrollPane makeScrollPane(VBox vBox) {
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        return scrollPane;
    }
}
