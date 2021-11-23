package sample;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TopPane {

    Pane pane;
    TopMenu topMenu;
    PenToolOptions penToolOptions;
    EraserToolOptions eraserToolOptions;
    BucketToolOptions bucketToolOptions;
    ColorReplacerToolOptions colorReplacerToolOptions;
    RectangleToolOptions rectangleToolOptions;

    TopPane()
    {
        pane = new Pane();
        pane.setPrefSize(GUI.getScreen_Resolution_Width(), GUI.getScreen_Resolution_Height() * 0.07);
        pane.setBackground(new Background(new BackgroundFill(Color.web("555555"), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.NONE,
                BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(2), Insets.EMPTY)));

        topMenu = new TopMenu();
        penToolOptions = new PenToolOptions();
        eraserToolOptions = new EraserToolOptions();
        bucketToolOptions = new BucketToolOptions();
        colorReplacerToolOptions = new ColorReplacerToolOptions();
        rectangleToolOptions = new RectangleToolOptions();

        pane.getChildren().addAll(topMenu.getMenuBar(), penToolOptions.getGroup(), eraserToolOptions.getGroup(), bucketToolOptions.getGroup(),
                colorReplacerToolOptions.getGroup(), rectangleToolOptions.getGroup());
    }

    public Pane getPane()
    {
        return pane;
    }

    public PenToolOptions getPenToolOptions()
    {
        return penToolOptions;
    }

    public EraserToolOptions getEraserToolOptions()
    {
        return eraserToolOptions;
    }

    public BucketToolOptions getBucketToolOptions()
    {
        return bucketToolOptions;
    }

    public ColorReplacerToolOptions getColorReplacerToolOptions()
    {
        return colorReplacerToolOptions;
    }

    public RectangleToolOptions getRectangleToolOptions()
    {
        return rectangleToolOptions;
    }
}
