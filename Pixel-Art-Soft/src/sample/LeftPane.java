package sample;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LeftPane {

    Pane pane;
    Rectangle selectedColorRectangle;
    double rectangleSize = 60;
    ColorPickerClass colorPickerClass;
    String selectedTool;

    PenTool penTool;
    EraserTool eraserTool;
    BucketTool bucketTool;
    ColorReplacerTool colorReplacerTool;
    RectangleTool rectangleTool;

    LeftPane()
    {
        pane = new Pane();
        pane.setPrefSize(GUI.getScreen_Resolution_Width() * 0.05, GUI.getScreen_Resolution_Height() * 0.85);
        pane.setBackground(new Background(new BackgroundFill(Color.web("555555"), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.NONE,
                BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(2), Insets.EMPTY)));

        colorPickerClass = new ColorPickerClass();
        selectedTool = "None";
        penTool = new PenTool();
        eraserTool = new EraserTool();
        bucketTool = new BucketTool();
        colorReplacerTool = new ColorReplacerTool();
        rectangleTool = new RectangleTool();

        pane.getChildren().addAll(colorPickerClass.getColorPicker(), penTool.getButton(), penTool.getImageView(), eraserTool.getButton(), eraserTool.getImageView(),
                bucketTool.getButton(), bucketTool.getImageView(), colorReplacerTool.getButton(), colorReplacerTool.getImageView(), rectangleTool.getButton(),
                rectangleTool.getImageView());
    }

    public void instantiateSelectedColorRectangle() {
        selectedColorRectangle = new Rectangle(pane.getPrefWidth() / 2 - rectangleSize / 2, pane.getLayoutY() + 20, rectangleSize, rectangleSize);
        selectedColorRectangle.setFill(Color.BLACK);
    }


    public void setSelectedTool(String toolName) {
        selectedTool = toolName;

        if(toolName == "Pen")
        {
            eraserTool.deactivateTool();
            bucketTool.deactivateTool();
            colorReplacerTool.deactivateTool();
            rectangleTool.deactivateTool();
        }
        else if(toolName == "Eraser")
        {
            penTool.deactivateTool();
            bucketTool.deactivateTool();
            colorReplacerTool.deactivateTool();
            rectangleTool.deactivateTool();
        }
        else if(toolName == "Bucket")
        {
            penTool.deactivateTool();
            eraserTool.deactivateTool();
            colorReplacerTool.deactivateTool();
            rectangleTool.deactivateTool();
        }
        else if(toolName == "Color Replacer")
        {
            penTool.deactivateTool();
            eraserTool.deactivateTool();
            bucketTool.deactivateTool();
            rectangleTool.deactivateTool();
        }
        else if(toolName == "Rectangle Tool")
        {
            penTool.deactivateTool();
            eraserTool.deactivateTool();
            bucketTool.deactivateTool();
            colorReplacerTool.deactivateTool();
        }
    }


    public Pane getPane() {
        return pane;
    }

    public ColorPickerClass getColorPickerClass() {
        return colorPickerClass;
    }

    public PenTool getPenTool() {
        return penTool;
    }

    public EraserTool getEraserTool() {
        return eraserTool;
    }

    public BucketTool getBucketTool() {
        return bucketTool;
    }

    public RectangleTool getRectangleTool() {
        return rectangleTool;
    }

    public ColorReplacerTool getColorReplacerTool() {
        return colorReplacerTool;
    }

    public String getSelectedTool() {
        return selectedTool;
    }


}
