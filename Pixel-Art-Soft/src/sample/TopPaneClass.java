package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class TopPaneClass {

    Pane pane;

    TopPaneClass()
    {
        pane = new Pane();
        pane.setPrefSize(GUI.getScreen_Resolution_Width(), GUI.getScreen_Resolution_Height() * 0.05);
        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public Pane getPane()
    {
        return pane;
    }
}
