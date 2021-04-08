package sample;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Canvas {

    Rectangle[][] rectanglesMatrix;
    ColorMatrix colorMatrix;
    double paneCenterX;
    double paneCenterY;
    double drawingWidth;
    double drawingHeight;
    double squareSize = 30;
    WritableImage writableImage;

    Canvas(double paneWidth, double paneHeight)
    {
        rectanglesMatrix = new Rectangle[16][16];
        colorMatrix = new ColorMatrix();
        writableImage = new WritableImage(128, 128);

        drawingHeight = 16 * squareSize;
        drawingWidth = 16 * squareSize;
        paneCenterX = paneWidth / 2;
        paneCenterY = paneHeight / 2;
        for(int i = 0; i < 16; i++)
            for(int j = 0; j < 16; j++)
                instantiateRectangle(i, j);

    }

    private void instantiateRectangle(int i, int j)
    {
        rectanglesMatrix[i][j] = new Rectangle((paneCenterX - drawingWidth / 2) + i * squareSize,
                (paneCenterY - drawingHeight / 2) + j * squareSize, squareSize, squareSize);
        rectanglesMatrix[i][j].setStroke(Color.BLACK);
        final Rectangle rectangle = rectanglesMatrix[i][j];
        rectangle.setFill(Color.WHITE);
        rectangleColorChanger(i, j, rectangle);
    }

    public Rectangle getRectangle(int i, int j)
    {
        return rectanglesMatrix[i][j];
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
                    composeImage();
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
            public void handle(MouseDragEvent mouseDragEvent) {
                if(GUI.getLeftPane().getSelectedTool() == "Pen")
                {
                    rectangle.setFill(GUI.getLeftPane().getColorPickerClass().getColorPicker().getValue());
                    colorMatrix.setMatrixElement(i, j, GUI.getLeftPane().getColorPickerClass().getColorPicker().getValue());
                }

                composeImage();
            }
        });

        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(GUI.getLeftPane().getSelectedTool() == "Pen")
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

    public void composeImage()
    {
        PixelWriter pixelWriter = this.writableImage.getPixelWriter();

        for(int i = 0; i < 16; i ++)
            for(int j = 0; j < 16; j ++)
            {
                for(int k = 0 ; k < 8 ; k++)
                    for(int t = 0; t < 8; t++)
                        pixelWriter.setColor(i * 8+ k, j * 8 + t, colorMatrix.getMatrixElement(i, j));
            }

        GUI.getBottomPane().addImageToPane(getImage());
    }

    public Image getImage()
    {
        Image image = writableImage;
        return image;
    }
}
