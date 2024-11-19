import java.text.NumberFormat;

import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.util.converter.NumberStringConverter;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import javafx.scene.layout.GridPane;

import javafx.scene.text.Text;

public class ScoringDialog
extends Application {
    private ComboBox<String> matchField;
    private ComboBox<Integer> stageField;
    private ComboBox<String> competitorField;

    private Spinner<Integer> missesField;
    private Spinner<Integer> proceduralsField;

    private Spinner<Integer> aHitsField;
    private Spinner<Integer> cHitsField;
    private Spinner<Integer> dHitsField;

    private TextField timeField;
    private TextFormatter<Number> timeFormatter;

    @Override
    public void start(Stage stage) {
        matchField = new ComboBox<>(
            FXCollections.observableArrayList("m1", "m2", "m3"));

        stageField = new ComboBox<>(
            FXCollections.observableArrayList(1, 2, 3, 4, 5, 7, 8, 9, 10));

        competitorField = new ComboBox<>(
            FXCollections.observableArrayList(
                "l1, m1", "l1, m2", "l1, m3",
                "l2, m1", "l2, m2", "l2, m3",
                "l3, m1", "l3, m2", "l3, m3"));

        matchField.getSelectionModel().selectFirst();
        stageField.getSelectionModel().selectFirst();
        competitorField.getSelectionModel().selectFirst();

        missesField = new Spinner<>(0, Integer.MAX_VALUE, 0);
        proceduralsField = new Spinner<>(0, Integer.MAX_VALUE, 0);

        aHitsField = new Spinner<>(0, Integer.MAX_VALUE, 0);
        cHitsField = new Spinner<>(0, Integer.MAX_VALUE, 0);
        dHitsField = new Spinner<>(0, Integer.MAX_VALUE, 0);

        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(1);
        format.setMaximumFractionDigits(1);
        timeFormatter = new TextFormatter<>(new NumberStringConverter(format));
        timeFormatter.setValue(111.0);

        timeField = new TextField();
        timeField.setTextFormatter(timeFormatter);
        timeField.setEditable(false);


        Spinner<?>[] spinners = {
            missesField,
            proceduralsField,
            aHitsField,
            cHitsField,
            dHitsField,
        };
        for (Spinner<?> spinner : spinners) {
            spinner.getStyleClass().add(
                Spinner.STYLE_CLASS_ARROWS_ON_RIGHT_HORIZONTAL);
        }

        Label matchLabel = new Label("Match: ");
        matchLabel.setLabelFor(matchField);

        Label stageLabel = new Label("Stage: ");
        stageLabel.setLabelFor(stageField);

        Label competitorLabel = new Label("Competitor: ");
        competitorLabel.setLabelFor(competitorField);

        Label missesLabel = new Label("Misses: ");
        missesLabel.setLabelFor(missesField);

        Label proceduralsLabel = new Label("Procedurals: ");
        proceduralsLabel.setLabelFor(proceduralsField);

        Label aHitsLabel = new Label("A Hits: ");
        aHitsLabel.setLabelFor(aHitsField);

        Label cHitsLabel = new Label("C Hits: ");
        cHitsLabel.setLabelFor(cHitsField);

        Label dHitsLabel = new Label("D Hits: ");
        dHitsLabel.setLabelFor(dHitsField);

        Label timeLabel = new Label("Total time: ");
        timeLabel.setLabelFor(timeField);

        Node coordinatesHeading = createHeading("Coordinates");
        Node penaltiesHeading = createHeading("Penalties");
        Node scoringHeading = createHeading("Scoring");
        Node timeHeading = createHeading("Time");

        Label horizontalSpacer = new Label();
        Label verticalSpacer = new Label();

        Button confirmButton = new Button("Confirm");

        GridPane grid = new GridPane(0, 3);

        grid.add(coordinatesHeading, 0, 0, 3, 1);
        grid.add(matchLabel, 1, 1);
        grid.add(matchField, 2, 1);
        grid.add(stageLabel, 1, 2);
        grid.add(stageField, 2, 2);
        grid.add(competitorLabel, 1, 3);
        grid.add(competitorField, 2, 3);

        grid.add(penaltiesHeading, 0, 5, 3, 1);
        grid.add(new Label("  "), 0, 6);
        grid.add(missesLabel, 1, 6);
        grid.add(missesField, 2, 6);
        grid.add(proceduralsLabel, 1, 7);
        grid.add(proceduralsField, 2, 7);

        grid.add(scoringHeading, 4, 0, 3, 1);
        grid.add(new Label("  "), 4, 1);
        grid.add(aHitsLabel, 5, 1);
        grid.add(aHitsField, 6, 1);
        grid.add(cHitsLabel, 5, 2);
        grid.add(cHitsField, 6, 2);
        grid.add(dHitsLabel, 5, 3);
        grid.add(dHitsField, 6, 3);

        grid.add(timeHeading, 4, 5, 3, 1);
        grid.add(new Label("  "), 4, 6);
        grid.add(timeLabel, 5, 6);
        grid.add(timeField, 6, 6);

        grid.add(horizontalSpacer, 3, 0, 1, GridPane.REMAINING);
        grid.add(verticalSpacer, 0, 4, GridPane.REMAINING, 1);
        GridPane.setMargin(horizontalSpacer, new Insets(0, 12, 0, 0));
        GridPane.setMargin(verticalSpacer, new Insets(12, 0, 0, 0));

        grid.add(confirmButton, 0, 8, GridPane.REMAINING, 1);

        GridPane.setHalignment(confirmButton, HPos.RIGHT);
        GridPane.setMargin(confirmButton, new Insets(6, 0, 0, 0));

        grid.setPadding(new Insets(6));

        stage.setScene(new Scene(grid));
        stage.setTitle("Rapid Scoring");
        stage.show();
    }

    private Node createHeading(String text) {
        Text heading = new Text(text);
        heading.setStyle("-fx-font-size: 120%;");
        return heading;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
