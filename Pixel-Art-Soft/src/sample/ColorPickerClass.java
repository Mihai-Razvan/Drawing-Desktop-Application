package sample;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.awt.*;
import java.beans.EventHandler;

public class ColorPickerClass {

    ColorPicker colorPicker;

    ColorPickerClass()
    {
        colorPicker = new ColorPicker(Color.BLACK);
        colorPicker.getStyleClass().add("button");
        colorPicker.setStyle("-fx-color-label-visible: false");
        colorPicker.setScaleX(1.9);
        colorPicker.setScaleY(2.3);
        colorPicker.setLayoutX(33);
        colorPicker.setLayoutY(37);
    }

    public ColorPicker getColorPicker()
    {
        return colorPicker;
    }

    public void setOpacity(double opacity)
    {
        colorPicker.setOpacity(opacity);
    }
}
