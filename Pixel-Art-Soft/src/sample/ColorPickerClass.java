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

        colorChangeListener();
    }

    public void colorChangeListener()
    {
        colorPicker.valueProperty().addListener((o, oldColor, newColor) -> {
            if(oldColor.getRed() != newColor.getRed() || oldColor.getGreen() != newColor.getGreen() ||oldColor.getBlue() != newColor.getBlue()) //verf daca ai schimbat culoare si nu doar opacitatea
            {
                GUI.getTopPane().getPenToolOptions().setOpacityTF(100);
                GUI.getTopPane().getRectangleToolOptions().setOpacityTF(100);
            }
        });
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
