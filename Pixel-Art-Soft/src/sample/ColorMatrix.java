package sample;

import javafx.scene.paint.Color;

public class ColorMatrix {

    Color[][] matrix;
    double[] pixelArray;

    ColorMatrix()
    {
        matrix = new Color[16][16];
        for(int i = 0; i < 16; i++)
            for(int j = 0; j < 16; j++)
                matrix[i][j] = Color.WHITE;

       pixelArray = new double[256];
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
