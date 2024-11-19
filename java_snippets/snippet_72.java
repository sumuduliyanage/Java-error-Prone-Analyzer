import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TitledGridContainer extends VBox {
    private final List<TitledGrid> managedGrids;

    public TitledGridContainer(double spacing, TitledGrid... titledGrid) {
        super(spacing, titledGrid);

        managedGrids = Arrays.asList(titledGrid);
    }

    public void sizeLabels() {
        double longestLabelWidth = calcRequiredLabelSize();

        getAllLabels().forEach(label ->
                label.setMinWidth(longestLabelWidth)
        );
    }

    public void sizeLabelColumn() {
        double longestLabelWidth = calcRequiredLabelSize();

        managedGrids.forEach(titledGrid ->
                titledGrid
                        .getLabelColumnConstraints()
                        .setMinWidth(longestLabelWidth)
        );
    }

    private double calcRequiredLabelSize() {
        if (getScene() == null || getScene().getWindow() == null) {
            throw new IllegalStateException(
                    "Titled grid label sizing can only be performed when the grid is in a scene displayed in a window, scene=%s, window=%s"
                            .formatted(
                                    getScene(),
                                    getScene() == null
                                            ? null
                                            : getScene().getWindow()
                            )
            );
        }

        applyCss();
        layout();

        return getAllLabels()
                .map(Region::getWidth)
                .reduce(Double::max)
                .orElse(0d);
    }

    private Stream<Label> getAllLabels() {
        return managedGrids.stream()
                .flatMap(grid ->
                        grid.getLabels().stream()
                );
    }
}
