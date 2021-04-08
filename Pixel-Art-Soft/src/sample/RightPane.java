package sample;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class RightPane {

    Pane pane;

    RightPane()
    {
        pane = new Pane();
        pane.setPrefSize(GUI.getScreen_Resolution_Width() * 0.1, GUI.getScreen_Resolution_Height() * 0.85);
        pane.setBackground(new Background(new BackgroundFill(Color.web("777777"), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.NONE,
                BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2), Insets.EMPTY)));
    }

    public Pane getPane()
    {
        return pane;
    }
}
