import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        List<Label> labelList = new ArrayList();
        
        GridPane gridPane1 = new GridPane();
        Label label1 = new Label("One two three");
        labelList.add(label1);
        gridPane1.add(label1, 0, 0);
        gridPane1.add(new TextField(), 1, 0);
        gridPane1.setHgap(20);
        var titledPane1 = new TitledPane("Top", gridPane1);
        titledPane1.setCollapsible(false);

        GridPane gridPane2 = new GridPane();
        Label label2 = new Label("One two three");
        labelList.add(label2);
        gridPane2.setHgap(20);
        gridPane2.add(label2, 0, 0);
        gridPane2.add(new TextField(), 1, 0);
        var titledPane2 = new TitledPane("Bottom", gridPane2);
        titledPane2.setCollapsible(false);

        findLongestLabelWidthAndSetLabelMinWidth(labelList);
        
        Scene scene = new Scene(new VBox(titledPane1, titledPane2), 400, 200);
        stage.setScene(scene);
        stage.show();
    }

    void findLongestLabelWidthAndSetLabelMinWidth(List<Label> labelList)
    {
        double maxWidth = 0;
        for (Label label : labelList) 
        {
            Text text = new Text(label.getText());
            double textWidth = text.getLayoutBounds().getWidth();
            if (textWidth > maxWidth) {
                maxWidth = textWidth;
            }
        }
        
        for (Label label : labelList) 
        {
            label.setMinWidth(maxWidth);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
