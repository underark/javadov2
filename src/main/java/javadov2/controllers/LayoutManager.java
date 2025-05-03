package javadov2.controllers;

import javadov2.enums.LayoutType;
import javadov2.interfaces.Builder;
import javadov2.objects.LayoutBundle;
import javadov2.utilities.LayoutSwitcher;
import javafx.scene.layout.StackPane;

import java.util.Map;

public class LayoutManager {
    private Builder layoutBuilder;
    public LayoutSwitcher layoutSwitcher;
    private StackPane root;
    private Map<LayoutType, LayoutBundle> layouts;

    public LayoutManager(Builder layoutBuilder) {
        this.layoutBuilder = layoutBuilder;
    }

    public void initialize() {
        root = layoutBuilder.giveRoot();
        layouts = layoutBuilder.build();
        layoutSwitcher = new LayoutSwitcher(root, layouts);
    }

    public StackPane giveRoot() {
        return root;
    }
}
