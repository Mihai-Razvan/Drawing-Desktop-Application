package sample;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class CenterPane {

    Pane pane;
    Canvas canvas;

    CenterPane()
    {
        pane = new Pane();
        pane.setPrefSize(GUI.getScreen_Resolution_Width() * 0.85, GUI.getScreen_Resolution_Height() * 0.72);
        pane.setBackground(new Background(new BackgroundFill(Color.web("222222"), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));

        canvas = new Canvas(pane.getPrefWidth(), pane.getPrefHeight());

        for(int i = 0; i < 16; i++)
            for(int j = 0; j < 16; j++)
                pane.getChildren().add(canvas.getRectangle(i, j));
    }

    public Pane getPane()
    {
        return pane;
    }

    public Canvas getCanvas()
    {
        return canvas;
    }

}
