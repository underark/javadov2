package javadov2.interfaces;

import javadov2.enums.LayoutType;
import javadov2.objects.LayoutBundle;

import java.util.Map;

public interface Manager {
    Map<LayoutType, LayoutBundle> giveLayouts();
}
