package sample;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LeftPaneClass {

    Pane pane;
    Rectangle selectedColorRectangle;
    double rectangleSize = 60;
    ColorPickerClass colorPickerClass;

    LeftPaneClass()
    {
        pane = new Pane();
        pane.setPrefSize(GUI.getScreen_Resolution_Width() * 0.05, GUI.getScreen_Resolution_Height() * 0.85);
        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGREY, CornerRadii.EMPTY, Insets.EMPTY)));

    //    instantiateSelectedColorRectangle();
        colorPickerClass = new ColorPickerClass();

        pane.getChildren().addAll(colorPickerClass.getColorPicker());
    }

    public void instantiateSelectedColorRectangle()
    {
        selectedColorRectangle = new Rectangle(pane.getPrefWidth() / 2 - rectangleSize / 2, pane.getLayoutY() + 20, rectangleSize, rectangleSize);
        selectedColorRectangle.setFill(Color.BLACK);
    }


    public Pane getPane()
    {
        return pane;
    }

    public ColorPickerClass getColorPickerClass()
    {
        return colorPickerClass;
    }
}
