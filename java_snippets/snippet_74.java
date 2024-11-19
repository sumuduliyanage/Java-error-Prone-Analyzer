import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class TitledGrid extends TitledPane {
    private final List<Label> labels = new ArrayList<>();
    private final ColumnConstraints labelColumnConstraints = new ColumnConstraints();

    public TitledGrid(String title, String... labelText) {
        super();
        setText(title);
        setCollapsible(false);

        GridPane gridPane = new GridPane();
        setContent(gridPane);
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.getColumnConstraints().add(labelColumnConstraints);

        int row = 0;
        for (String text: labelText) {
            Label label = new Label(text);
            labels.add(label);

            gridPane.addRow(row++, label, new TextField());
        }
    }

    public List<Label> getLabels() {
        return labels;
    }

    public ColumnConstraints getLabelColumnConstraints() {
        return labelColumnConstraints;
    }
}
