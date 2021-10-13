package sample;

import javafx.scene.paint.Color;

import java.awt.*;

public class ColorMatrix {

    Color[][] matrix;

    ColorMatrix(int tileWidth, int tileHeight)
    {
        matrix = new Color[tileHeight][tileWidth];
        for(int i = 0; i < tileHeight; i++)
            for(int j = 0; j < tileWidth; j++)
                matrix[i][j] = Color.TRANSPARENT;
    }


    public void setMatrixElement(int i, int j, Color color)
    {
        matrix[i][j] = color;
    }

    public Color getMatrixElement(int i, int j)
    {
        return matrix[i][j];
    }

}
