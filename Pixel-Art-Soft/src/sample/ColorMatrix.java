package sample;

import javafx.scene.paint.Color;

import java.awt.*;

public class ColorMatrix {

    Color[][] matrix;

    ColorMatrix()
    {
        matrix = new Color[16][16];
        for(int i = 0; i < 16; i++)
            for(int j = 0; j < 16; j++)
                matrix[i][j] = Color.TRANSPARENT;
    }

    public Color getMatrixElement(int i, int j)
    {
        return matrix[i][j];
    }

    public void setMatrixElement(int i, int j, Color color)
    {
        matrix[i][j] = color;
    }

}
