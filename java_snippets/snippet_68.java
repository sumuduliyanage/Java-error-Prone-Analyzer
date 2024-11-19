import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class GridPane_Demo extends Application {

    @Override
    public void start(Stage stage) {


        GridPane gridPane1 = buildGrid("One", "One Two");
        var titledPane1 = new TitledPane("Top", gridPane1);
        titledPane1.setCollapsible(false);

        GridPane gridPane2 = buildGrid("One Two Three", "One Two Three Four");
        var titledPane2 = new TitledPane("Bottom", gridPane2);
        titledPane2.setCollapsible(false);

        buildFirstColumnConstraint(gridPane1, gridPane2);
        Scene scene = new Scene(new VBox(titledPane1, titledPane2), 400, 220);
        stage.setScene(scene);
        stage.show();
    }

    private GridPane buildGrid(String label1, String label2) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(5);
        gridPane.addRow(0, new Label(label1), new TextField());
        gridPane.addRow(1, new Label(label2), new TextField());
        return gridPane;
    }

    /**
     * Builds a common first column constraints for the provided gridPanes.
     *
     * @param gridPanes
     */
    private static void buildFirstColumnConstraint(GridPane... gridPanes) {
        /* Column constraint key. */
        final String COLUMN_INDEX_CONSTRAINT = "gridpane-column";

        /* Checks if the node is a first column node or not. */
        Predicate<Node> isFirstColumn = node -> {
            Integer constraint = (Integer) node.getProperties().get(COLUMN_INDEX_CONSTRAINT);
            return constraint != null && constraint == 0;
        };

        /* Keep track of the max width. */
        DoubleProperty maxWidth = new SimpleDoubleProperty();
        ChangeListener<Number> widthListener = (obs, old, val) -> {
            if (val.doubleValue() > maxWidth.get()) {
                maxWidth.set(val.doubleValue());
            }
        };

        // Bind the minWidth to the calculated width
        final ColumnConstraints constraint = new ColumnConstraints();
        constraint.minWidthProperty().bind(maxWidth);

        // Go through each gridPane and set the first constraint
        Stream.of(gridPanes).forEach(gridPane -> {
            gridPane.getColumnConstraints().add(0, constraint);

            // Filter for all first column children and add the widthListener to them
            gridPane.getChildren().stream().filter(isFirstColumn)
                    .map(node -> (Region) node)
                    .forEach(region -> region.widthProperty().addListener(widthListener));
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
