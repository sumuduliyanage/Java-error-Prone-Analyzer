import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyGridPanes extends Application {
    @Override
    public void start(Stage stage) {
        TitledGridContainer layout = new TitledGridContainer(
                10,
                new TitledGrid("Top", "One two three"),
                new TitledGrid("Bottom", "Four five six seven", "")
        );
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 400, 200);
        stage.setScene(scene);
        stage.show();

        // an example of sizing all labels in all grids.
        //layout.sizeLabels();
        
        // alternate example of sizing columns of labels.
        layout.sizeLabelColumn();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
