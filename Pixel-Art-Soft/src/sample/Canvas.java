package sample;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.security.PublicKey;

public class Canvas {

    Rectangle[][] rectanglesMatrix;
    ColorMatrix colorMatrix;
    int tileWidth;
    int tileHeight;
    double paneWidth;
    double paneHeight;
    double paneCenterX;
    double paneCenterY;
    double squareSize;
    double canvasSize = 640;

    Canvas(int tileWidth, int tileHeight, double paneWidth, double paneHeight)
    {
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;
        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;

        rectanglesMatrix = new Rectangle[tileHeight][tileWidth];
        colorMatrix = new ColorMatrix(tileWidth, tileHeight);

        paneCenterX = paneWidth / 2;
        paneCenterY = paneHeight / 2;
        squareSize = canvasSize / Integer.max(tileWidth, tileHeight);

        for(int i = 0; i < tileHeight; i++)
            for(int j = 0; j < tileWidth; j++)
                instantiateRectangle(i, j);

    }

    private void instantiateRectangle(int i, int j)
    {
        rectanglesMatrix[i][j] = new Rectangle(squareSize, squareSize);
        rectanglesMatrix[i][j].setLayoutX(paneCenterX - squareSize * (Double.valueOf(tileWidth) / 2) + j * squareSize);
        rectanglesMatrix[i][j].setLayoutY(paneCenterY - squareSize * (Double.valueOf(tileHeight) / 2) + i * squareSize);
        final Rectangle rectangle = rectanglesMatrix[i][j];
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(rectangle.getFill());     // ca sa nu ramana linia aia libera intre triunghiuri punem strokeu de ac culoare cu triunghiu
        rectangleColorChanger(i, j, rectangle);
    }

    private void rectangleColorChanger(int i, int j, Rectangle rectangle)   //asta e pt tooluri gen pen guma etc
    {
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(GUI.getLeftPane().getSelectedTool() == "Pen")
                {
                    rectangle.setFill(GUI.getLeftPane().getColorPickerClass().getColorPicker().getValue());
                    rectangle.setStroke(rectangle.getFill());
                    colorMatrix.setMatrixElement(i, j, GUI.getLeftPane().getColorPickerClass().getColorPicker().getValue());
                }
                else if(GUI.getLeftPane().getSelectedTool() == "Eraser")
                {
                    rectangle.setFill(Color.TRANSPARENT);
                    rectangle.setStroke(rectangle.getFill());
                    colorMatrix.setMatrixElement(i, j, Color.TRANSPARENT);
                }
                else if(GUI.getLeftPane().getSelectedTool() == "Bucket")
                {
                    BucketTool.bucketToolFill(i ,j, colorMatrix.getMatrixElement(i ,j), GUI.getLeftPane().getColorPickerClass().getColorPicker().getValue());
                }
                else if(GUI.getLeftPane().getSelectedTool() == "Color Replacer")
                {
                    if(event.isSecondaryButtonDown())        ///mouse sa aleaga culorile
                        GUI.getLeftPane().getColorReplacerTool().changeColors(colorMatrix.getMatrixElement(i, j));
                    else if(event.isPrimaryButtonDown())
                        GUI.getLeftPane().getColorReplacerTool().replaceColor(i, j);
                }

            }
        });

        rectangle.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectangle.startFullDrag();
            }
        });

        rectangle.setOnMouseDragOver(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                if(GUI.getLeftPane().getSelectedTool() == "Pen")
                {
                    rectangle.setFill(GUI.getLeftPane().getColorPickerClass().getColorPicker().getValue());
                    rectangle.setStroke(rectangle.getFill());
                    colorMatrix.setMatrixElement(i, j, GUI.getLeftPane().getColorPickerClass().getColorPicker().getValue());
                }
                else if(GUI.getLeftPane().getSelectedTool() == "Eraser")
                {
                    rectangle.setFill(Color.TRANSPARENT);
                    rectangle.setStroke(rectangle.getFill());
                    colorMatrix.setMatrixElement(i ,j, Color.TRANSPARENT);
                }
                else if(GUI.getLeftPane().getSelectedTool() == "Color Replacer")
                {
                    if(event.isPrimaryButtonDown())
                        GUI.getLeftPane().getColorReplacerTool().replaceColor(i ,j);
                }

            }
        });

        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(GUI.getLeftPane().getSelectedTool() == "Pen")
                    rectangle.setFill(GUI.getLeftPane().getColorPickerClass().getColorPicker().getValue());
                else if(GUI.getLeftPane().getSelectedTool() == "Eraser")
                    rectangle.setFill(Color.WHITE);
                else if(GUI.getLeftPane().getSelectedTool() == "Bucket")
                    rectangle.setFill(GUI.getLeftPane().getColorPickerClass().getColorPicker().getValue());
                else if(GUI.getLeftPane().selectedTool == "Color Replacer")
                {
                    if(GUI.getLeftPane().getColorReplacerTool().getColorToReplace() == rectangle.getFill())
                        rectangle.setFill(GUI.getLeftPane().getColorReplacerTool().getReplacementColor());
                }
            }
        });

        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectangle.setFill(colorMatrix.getMatrixElement(i, j));
            }
        });

    }

    public void setRectangleFill(int i, int j, Color color)      //asta e ca sa setezi culoarea unui rectangle
    {
        rectanglesMatrix[i][j].setFill(color);
        rectanglesMatrix[i][j].setStroke(color);
    }

    public ColorMatrix getColorMatrix()
    {
        return colorMatrix;
    }

    public Rectangle getRectangle(int i, int j)
    {
        return rectanglesMatrix[i][j];
    }

    public int getTileWidth()
    {
        return tileWidth;
    }

    public int getTileHeight()
    {
        return tileHeight;
    }
}
