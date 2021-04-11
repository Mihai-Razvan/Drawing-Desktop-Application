package sample;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TopPane {

    Pane pane;
    ProjectMenu projectMenu;
    PenToolOptions penToolOptions;

    TopPane()
    {
        pane = new Pane();
        pane.setPrefSize(GUI.getScreen_Resolution_Width(), GUI.getScreen_Resolution_Height() * 0.07);
        pane.setBackground(new Background(new BackgroundFill(Color.web("555555"), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.NONE,
                BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(2), Insets.EMPTY)));

        projectMenu = new ProjectMenu();
        penToolOptions = new PenToolOptions();

        pane.getChildren().addAll(projectMenu.getMenuBar(), penToolOptions.getGroup());
    }

    public Pane getPane()
    {
        return pane;
    }
}
