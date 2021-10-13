package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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


    public void setOpacity(double opacity)
    {
        opacity = opacity / 100;          //opacity e intre 1 si 100 si in setValue e intre 0 si 1
        colorPicker.setValue(new Color(colorPicker.getValue().getRed(), colorPicker.getValue().getGreen(), colorPicker.getValue().getBlue(), opacity));
    }


    public ColorPicker getColorPicker()
    {
        return colorPicker;
    }
}
