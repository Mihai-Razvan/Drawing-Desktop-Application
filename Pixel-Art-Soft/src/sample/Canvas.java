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

public class Canvas {

    Rectangle[][] rectanglesMatrix;
    Rectangle[][] backgroundRectanglesMatrix;       //alea de no fill
    ColorMatrix colorMatrix;
    Image noFillImage;
    int tileWidth;
    int tileHeight;
    double paneCenterX;
    double paneCenterY;
    double squareSize;
    double canvasSize = 640;
    int pixelsPerSquare = 16;      //pt ffiecare patratel cati pixeli deseneaza
    WritableImage writableImage;

    Canvas(int tileWidth, int tileHeight, double paneWidth, double paneHeight)
    {
        this.tileHeight = tileHeight;
        this.tileWidth = tileWidth;

        rectanglesMatrix = new Rectangle[tileHeight][tileWidth];
        backgroundRectanglesMatrix = new Rectangle[tileHeight][tileWidth];
        colorMatrix = new ColorMatrix(tileWidth, tileHeight);
        writableImage = new WritableImage(pixelsPerSquare * tileWidth, pixelsPerSquare * tileHeight);

        paneCenterX = paneWidth / 2;
        paneCenterY = paneHeight / 2;
        squareSize = canvasSize / Integer.max(tileWidth, tileHeight);

        noFillImage = new Image("no_fill.png");
        for(int i = 0; i < tileHeight; i++)
            for(int j = 0; j < tileWidth; j++)
                instantiateRectangle(i, j);

    }



    private void instantiateRectangle(int i, int j)
    {
        rectanglesMatrix[i][j] = new Rectangle((paneCenterX - canvasSize / 2) + i * squareSize,
                (paneCenterY - canvasSize / 2) + j * squareSize, squareSize, squareSize);
        rectanglesMatrix[i][j].setStroke(Color.BLACK);
        final Rectangle rectangle = rectanglesMatrix[i][j];
        rectangle.setFill(Color.TRANSPARENT);
        rectangleColorChanger(i, j, rectangle);

        backgroundRectanglesMatrix[i][j] = new Rectangle((paneCenterX - canvasSize / 2) + i * squareSize,
                (paneCenterY - canvasSize / 2) + j * squareSize, squareSize, squareSize);
        backgroundRectanglesMatrix[i][j].setFill(new ImagePattern(noFillImage));
    }


    private void rectangleColorChanger(int i, int j, Rectangle rectangle)
    {
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(GUI.getLeftPane().getSelectedTool() == "Pen")
                {
                    rectangle.setFill(GUI.getLeftPane().getColorPickerClass().getColorPicker().getValue());
                    colorMatrix.setMatrixElement(i, j, GUI.getLeftPane().getColorPickerClass().getColorPicker().getValue());
                }
                else if(GUI.getLeftPane().getSelectedTool() == "Eraser")
                {
                    rectangle.setFill(Color.TRANSPARENT);
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

                composeImage();
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
                    colorMatrix.setMatrixElement(i, j, GUI.getLeftPane().getColorPickerClass().getColorPicker().getValue());
                }
                else if(GUI.getLeftPane().getSelectedTool() == "Eraser")
                {
                    rectangle.setFill(Color.TRANSPARENT);
                    colorMatrix.setMatrixElement(i ,j, Color.TRANSPARENT);
                }
                else if(GUI.getLeftPane().getSelectedTool() == "Color Replacer")
                {
                    if(event.isPrimaryButtonDown())
                        GUI.getLeftPane().getColorReplacerTool().replaceColor(i ,j);
                }

                composeImage();
            }
        });

        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(GUI.getLeftPane().getSelectedTool() == "Pen")
                    rectangle.setFill(GUI.getLeftPane().getColorPickerClass().getColorPicker().getValue());
                else if(GUI.getLeftPane().getSelectedTool() == "Eraser")
                    rectangle.setFill(Color.TRANSPARENT);
                else if(GUI.getLeftPane().getSelectedTool() == "Bucket")
                    rectangle.setFill(GUI.getLeftPane().getColorPickerClass().getColorPicker().getValue());
            }
        });

        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rectangle.setFill(colorMatrix.getMatrixElement(i, j));
            }
        });

    }

    public void composeImage()     //functia asta nu doar compune imaginea ci o si adauga jos
    {
        PixelWriter pixelWriter = this.writableImage.getPixelWriter();

        for(int i = 0; i < tileHeight; i ++)
            for(int j = 0; j < tileWidth; j ++)
            {
                for(int k = 0 ; k < pixelsPerSquare ; k++)
                    for(int t = 0; t < pixelsPerSquare; t++)
                        pixelWriter.setColor(i * pixelsPerSquare + k, j * pixelsPerSquare + t, colorMatrix.getMatrixElement(i, j));
            }

        GUI.getBottomPane().updateImage(getImage());
    }


    public void setRectangleFill(int i, int j, Color color)
    {
        rectanglesMatrix[i][j].setFill(color);
    }


    public Image getImage()
    {
        Image image = writableImage;
        return image;
    }

    public ColorMatrix getColorMatrix()
    {
        return colorMatrix;
    }

    public Rectangle getRectangle(int i, int j)
    {
        return rectanglesMatrix[i][j];
    }

    public Rectangle getBackgroundRectangle(int i, int j)
    {
        return backgroundRectanglesMatrix[i][j];
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
